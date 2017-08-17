package app.util;

import org.apache.commons.lang3.StringUtils;

import spark.Filter;
import spark.Request;
import spark.Response;

public class Filters {

    // If a user manually manipulates paths and forgets to add
    // a trailing slash, redirect the user to the correct path
    public static Filter addTrailingSlashes = (Request request, Response response) -> {
        if (!request.pathInfo().endsWith("/")) {
            response.redirect(request.pathInfo() + "/");
        }
    };

    // Redirect requests for the root context to the index page
    public static Filter redirectRoot = (Request request, Response response) -> {
        if (request.pathInfo().equals("/") || StringUtils.isEmpty(request.pathInfo())) {
            response.redirect( "/index/");
        }
    };

    // Enable GZIP for all responses
    public static Filter addGzipHeader = (Request request, Response response) -> {
        response.header("Content-Encoding", "gzip");
    };

}
