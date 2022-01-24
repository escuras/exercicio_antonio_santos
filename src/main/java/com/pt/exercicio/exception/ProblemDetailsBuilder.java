package com.pt.exercicio.exception;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
class ProblemDetailsBuilder {
    private final Throwable throwable;

    protected ProblemDetails build() {
        ProblemDetails detail = new ProblemDetails();
        detail.setType(buildType());
        detail.setTitle(buildTitle());
        detail.setDetail(buildDetailMessage());
        detail.setStatus(buildStatus());
        detail.setInstance(buildInstance());
        return detail;
    }

    private URI buildType() {
        return URI.create("https://api.myshop.example/apidocs/" +
                javadocName(throwable.getClass()) + ".html");
    }

    private static String javadocName(Class<?> type) {
        return type.getName()
                .replace('.', '/') // the package names are delimited like a path
                .replace('$', '.'); // nested classes are delimited with a period
    }

    private String buildTitle() {
        return camelToWords(throwable.getClass().getSimpleName());
    }

    private static String camelToWords(String input) {
        return String.join(" ", input.split("(?=\\p{javaUpperCase})"));
    }

    private String buildDetailMessage() {
        Status status = throwable.getClass().getAnnotation(Status.class);
        if (status != null) {
            return status.message();
        }
        return "";
    }

    private int buildStatus() {
        Status status = throwable.getClass().getAnnotation(Status.class);
        if (status != null) {
            return status.value();
        }
        return INTERNAL_SERVER_ERROR.value();
    }

    private URI buildInstance() {
        return URI.create("urn:uuid:" + UUID.randomUUID());
    }
}