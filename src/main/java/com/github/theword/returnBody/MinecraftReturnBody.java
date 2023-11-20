package com.github.theword.returnBody;

public class MinecraftReturnBody {

    private String api;

    private String data;

    public MinecraftReturnBody() {
    }

    public MinecraftReturnBody(String api, String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
