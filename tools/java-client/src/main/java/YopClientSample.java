/*
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2021-04-17
 */
public class YopClientSample {
    //region 常量
    public static final String DEFAULT_CHARSET = "UTF-8";
    //endregion

    //region 配置信息
    /**
     * 应用标识
     */
    private static final String appKey = "";

    /**
     * 商户私钥
     */
    private static final String isvPrivateKey = "";

    /**
     * 平台公钥
     */
    private static final String yopPublicKey = "";

    /**
     * 连接超时时间
     */
    private static final int connectionTimeout = 30000;

    /**
     * 读取超时时间
     */
    private static final int readTimeout = 30000;
    //endregion

    //region 请求信息
    /**
     * 网关地址
     */
    private static final String endpoint = "";

    /**
     * 请求接口
     */
    private static final String apiUri = "";

    /**
     * 请求方法
     * 目前仅支持GET、POST
     */
    private static final String httpMethod = "";

    /**
     * 请求格式
     */
    private static final String contentType = "";
    //endregion

    public static void main(String[] args) {
        // region 1、构造请求
        // endregion

        // region 1.1、封装业务参数
        BaseRequest bizReq = null;
        // endregion

        // region 1.1.1、GET请求
        GetRequest getReq = new GetRequest();
        // endregion

        // region 1.1.2、POST请求
        // endregion

        // region 1.1.2.1、JSON方式
        JsonRequest jsonRequest = new JsonRequest();
        // endregion

        // region 1.1.2.2、FORM方式
        StandardFormRequest standardFormReq = new StandardFormRequest();
        // endregion

        // region 1.1.2.3、单文件流式上传
        BinaryRequest binaryReq = new BinaryRequest();
        // endregion

        // region 1.1.2.4、多文件FORM上传
        MultiPartFormRequest multiPartFormReq = new MultiPartFormRequest();
        // endregion

        // region 1.2、设置公共参数
        bizReq.headers.put("", "");
        // endregion

        // region 1.3、签名
        // endregion

        // region 1.4、加密
        // endregion

        // region 1.5、追加请求头
        // endregion

        // region 2、发起请求
        URL url = new URL(endpoint + apiUri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(connectionTimeout);
        conn.setReadTimeout(readTimeout);
        conn.setRequestMethod(httpMethod);
        conn.setDoInput(true);
        if (bizReq instanceof BodyRequest) {
            BodyRequest bodyBizReq = (BodyRequest) bizReq;
            if (null != bodyBizReq.content()) {
                conn.setDoOutput(true);
                bodyBizReq.contentType
            }
        }

        // endregion

        // region 3、处理响应
        //endregion

    }

    //region 业务参数封装类
    private abstract static class BaseRequest {
        /**
         * 请求头
         */
        public Map<String, String> headers = new LinkedHashMap<>();

        /**
         * url参数
         */
        public Map<String, Object> urlParams = new LinkedHashMap<>();

        abstract String httpMethod();
    }

    private abstract static class BodyRequest extends BaseRequest {
        /**
         * 内容类型
         */
        public String contentType;

        /**
         * 内容
         * @return
         */
        abstract Object content();

        @Override
        String httpMethod() {
            return "POST";
        }

    }

    private abstract static class EncodedBodyRequest extends BodyRequest {

        protected String encodedContent(Object content) {
            if (content instanceof Map) {
                return ((Map<String, Object>)content).entrySet().stream()
                        .map(kv -> kv.getKey() + "=" + urlEncode(kv.getValue()))
                        .collect(Collectors.joining("&"));

            } else if (content instanceof String) {
                return urlEncode(content);
            } else {
                return content + "";
            }
        }

        final String urlEncode(Object value) {
            try {
                if (null != value) {
                    return URLEncoder.encode(value + "", DEFAULT_CHARSET);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }

    }

    private static class GetRequest extends BaseRequest {
        @Override
        String httpMethod() {
            return "GET";
        }
    }

    private static class JsonRequest extends BodyRequest {
        public String content;

        @Override
        String content() {
            return content;
        }
    }

    private static class BinaryRequest extends BodyRequest {
        public InputStream content;

        @Override
        InputStream content() {
            return content;
        }
    }

    private static class StandardFormRequest extends EncodedBodyRequest {
        public Map<String, Object> plainParams = new LinkedHashMap<>();
        private String encodedPlainParams = null;

        @Override
        String content() {
            if (null == encodedPlainParams) {
                encodedPlainParams = encodedContent(plainParams);
            }
            return encodedPlainParams;
        }
    }

    private static class MultiPartFormRequest extends StandardFormRequest {
        public Map<String, InputStream> fileParams = new LinkedHashMap<>();

        @Override
        MultiPartFormRequest content() {
            return this;
        }
    }
    //endregion

    //region 请求体处理类
    private static final Map<String, BodyWriter> bodyWriters = new LinkedHashMap<>();
    static {
        bodyWriters.put("application/json", new JsonBodyWriter());
        bodyWriters.put("application/x-www-form-urlencoded", new StandardFormBodyWriter());
        bodyWriters.put("multipart/form-data", new MultipartFormBodyWriter());
    }

    private static interface BodyWriter<Request> {
        void write(OutputStream out, Request req) throws IOException;
    }

    private static class JsonBodyWriter implements BodyWriter<JsonRequest> {

        @Override
        public void write(OutputStream out, JsonRequest req) throws IOException {
            out.write(req.content.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();
        }
    }

    private static class BinaryBodyWriter implements BodyWriter<BinaryRequest> {

        @Override
        public void write(OutputStream out, BinaryRequest req) throws IOException {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = req.content.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
        }
    }

    private static class StandardFormBodyWriter implements BodyWriter<StandardFormRequest> {

        @Override
        public void write(OutputStream out, StandardFormRequest req) throws IOException {
            out.write(body.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();
        }
    }

    private static class MultipartFormBodyWriter implements BodyWriter<MultiPartFormRequest> {

        @Override
        public void write(OutputStream out, MultiPartFormRequest req) throws IOException {

        }
    }



    //endregion
}
