import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Table;
import org.kohsuke.args4j.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wdc
 * @date 2018/4/13
 */
public class Jdbc {
    @Option(name = "-user", usage = "用户名")
    private String username = "root";
    @Option(name = "-pwd", usage = "密码")
    private String password = "mysql";
    @Option(name = "-host", usage = "服务器地址")
    private String host = "localhost";
    @Option(name = "-port", usage = "端口号")
    private String port = "3306";
    @Option(name = "-db", usage = "数据库", required = true)
    private String db;
    @Option(name = "-bach", usage = "批量操作")
    private boolean bach = false;
    @Option(name = "-sql", usage = "sql语句")
    private String sql = "select version()";
    @Option(name = "-width", usage = "表格宽度")
    private int width = 20;
    @Option(name = "-console", usage = "打开console")
    private boolean console = false;
    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<>();

    public static void main(String[] args) {
        new Jdbc().doMain(args);
    }

    private void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this, ParserProperties.defaults());
        try {
            System.out.println("解析命令行参数…………");
            parser.parseArgument(args);
            System.out.println("其他命令行参数…………");
            arguments.stream().forEach(System.out::println);
        } catch (CmdLineException e) {
            System.out.println("参数有误：" + e.getMessage());
            parser.printUsage(System.err);
            return;
        }
        doSql();
    }

    private void doSql() {
        String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8&useSSL=false", host, port, db);
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement statement = conn.createStatement()) {
            Class.forName("com.mysql.jdbc.Driver");
            if (console) {
                System.out.print("请输入sql，支持批量操作:\nmysql>");
                try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
                    while ((sql = bf.readLine()) != null) {
                        sql = sql.trim();
                        if (sql.equals("exit")) break;
                        if (sql.startsWith("setWidth:")) {
                            setWidth(Integer.valueOf(sql.substring(9)));
                            continue;
                        }
                        try {
                            execSql(statement, sql);
                        } catch (SQLException e) {
                            System.err.println(e.getMessage());
                            System.out.println("请输入正确的sql！！！！");
                        }
                        System.out.println("======================================================================================================================================================");
                        System.out.print("mysql>");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                execSql(statement, sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execSql(Statement statement, String sql) throws SQLException {
        String[] split = sql.split(";");
        if (split.length > 1) {
            for (String s : split) {
                statement.addBatch(s);
            }
            System.out.format("执行sql：%s\n执行结果：%s(%d)\n", sql, Arrays.toString(statement.executeBatch()), statement.getUpdateCount());
        } else {
            System.out.format("执行sql：%s\n执行结果：%s(%d)\n", split[0], statement.execute(split[0]), statement.getUpdateCount());
        }
        printRs(statement);
    }

    private void printRs(Statement statement) throws SQLException {
        try (ResultSet rs = statement.getResultSet()) {
            if (null != rs) {
                List<String> headers = Collections.emptyList();
                List<List<String>> rows = new ArrayList<>(50);
                int rowCount = 1;
                while (rs.next()) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    if (rowCount++ == 1) {
                        headers = Stream.iterate(1, n -> n + 1).limit(columnCount)
                                .map(ci -> {
                                    try {
                                        return metaData.getColumnName(ci) + "(" + metaData.getColumnTypeName(ci) + ")";
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }).collect(Collectors.toList());
                    }

                    rows.add(Stream.iterate(1, n -> n + 1).limit(columnCount)
                            .map(ci -> {
                                try {
                                    return rs.getString(ci) == null ? "null" : rs.getString(ci);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                return "null";
                            }).collect(Collectors.toList()));
                }
                int cols = headers.size();
                int size = rows.size();
                String[][] data = new String[size][cols];
                Stream.iterate(0, row -> row + 1).limit(size)
                        .forEach(row -> Stream.iterate(0, col -> col + 1)
                                .limit(cols)
                                .forEach(col -> data[row][col] = rows.get(row).get(col)));
                String[] tHeader = headers.toArray(new String[0]);
                ColumnFormatter<String> text = ColumnFormatter.text(Alignment.LEFT, width);
                System.out.println(Table.of(tHeader, data, text));
            }
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
