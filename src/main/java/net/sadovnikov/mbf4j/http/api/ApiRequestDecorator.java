package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.ApiException;
import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.http.api.ApiRequest;
import net.sadovnikov.mbf4j.http.api.response.ApiResponse;

public abstract class ApiRequestDecorator {

    public abstract ApiRequest execute(ApiRequest request) throws ApiException,HttpException;
}
