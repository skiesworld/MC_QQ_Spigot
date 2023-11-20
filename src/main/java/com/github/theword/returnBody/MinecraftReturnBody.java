package com.github.theword.returnBody;

import com.google.gson.JsonElement;

public class MinecraftReturnBody {

    private String api;

    private JsonElement data;

    public MinecraftReturnBody() {
    }

    public MinecraftReturnBody(String api, JsonElement data) {
        this.api = api;
        this.data = data;
    }

    @Override
    public String toString() {
        return "MinecraftReturnBody{" +
                "api='" + api + '\'' +
                ", data=" + data +
                '}';
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
