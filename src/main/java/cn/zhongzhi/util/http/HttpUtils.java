package cn.zhongzhi.util.http;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author suchengbo
 * @Date 2025/3/21 15:04
 */
@Slf4j
public class HttpUtils {

    public static String sendPostRequest(String url, String apiKey, Map<String, Object> params) {
        // 1. 构建请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Content-Type", "application/json");

        // 2. 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        // 将参数放入 inputs
        requestBody.put("inputs", params);
        // 固定用户参数
        requestBody.put("user", "xiaosu");

        try {
            // 3. 发送 POST 请求
            HttpResponse response = HttpRequest.post(url)
                    // 添加请求头
                    .headerMap(headers, true)
                    // 将请求体转为 JSON
                    .body(JSONUtil.toJsonStr(requestBody))
                    // 关键修改点：设置超时
                    .timeout(60_000)
                    .execute();

            // 4. 处理响应
            if (response.isOk()) {
                if (JSONUtil.isTypeJSONObject(response.body())){
                    JSONObject bodyJsonObject = JSONUtil.parseObj(response.body());
                    JSONObject dataJsonObject = bodyJsonObject.getJSONObject("data");
                    if (dataJsonObject != null){
                        JSONObject outputJsonObject = dataJsonObject.getJSONObject("outputs");
                        if (outputJsonObject != null){
                            log.info("AI结构化识别成功：{}", outputJsonObject.getStr("result"));
                            return outputJsonObject.getStr("result");
                        }
                    }
                }
                log.error("AI结构化识别失败，返回值非法：{}",response.body());
                return null;
            }
            log.error("AI结构化识别请求失败，状态码：{}" , response.getStatus());
            return null;
        } catch (Exception e) {
            log.error("AI结构化识别请求异常：",e);
        }
        return null;
    }
}
