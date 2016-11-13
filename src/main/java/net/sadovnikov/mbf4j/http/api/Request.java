package net.sadovnikov.mbf4j.http.api;

import net.sadovnikov.mbf4j.http.HttpException;
import net.sadovnikov.mbf4j.ApiException;

public interface Request {

    ApiResponseParser execute() throws ApiException, HttpException;
}
