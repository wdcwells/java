import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Table;
import org.kohsuke.args4j.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wdc
 * @date 2018/4/13
 */
public class Jdbc {
    @Option(name = "-u")
    private String username = "root";
    @Option(name = "-p")
    private String password = "mysql";
    @Option(name = "-h")
    private String host = "localhost";
    @Option(name = "-pt")
    private String port = "3306";
    @Option(name = "-db", required = true)
    private String db;
    @Option(name = "-sql", required = true)
    private String sql;
    @Option(name = "-w")
    private int width = 20;
    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<>();

    public static void main(String[] args) {
        new Jdbc().doMain(args);
    }

    private void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this, ParserProperties.defaults());
        try {
            System.out.println("解析参数…………");
            parser.parseArgument(args);
            System.out.println("其他参数…………");
            arguments.stream().forEach(System.out::println);
        } catch (CmdLineException e) {
            System.out.println("参数有误：" + e.getMessage());
            parser.printUsage(System.err);
            return;
        }
        doSql(host, port, db, username, password, sql, width);
    }

    private void doSql(String host, String port, String db, String username, String password, String sql, int width) {
        String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8&useSSL=false", host, port, db);
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement statement = conn.createStatement()) {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.format("执行sql：%s\n执行结果：%s(%d)\n", sql, statement.execute(sql), statement.getUpdateCount());
            ResultSet rs = statement.getResultSet();
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
                                    return rs.getString(ci);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                return null;
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
                rs.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
