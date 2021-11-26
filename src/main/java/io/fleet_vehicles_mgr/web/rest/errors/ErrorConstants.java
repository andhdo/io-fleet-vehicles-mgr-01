package io.fleet_vehicles_mgr.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {
    public static final String PROBLEM_BASE_URL =
            "https://localhost:8080/problem";
    public static final URI DEFAULT_TYPE = URI.create(
            PROBLEM_BASE_URL + "/problem-with-message"
    );
}
