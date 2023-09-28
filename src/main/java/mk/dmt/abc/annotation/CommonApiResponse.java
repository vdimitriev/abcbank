package mk.dmt.abc.annotation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "500", description = "Internal server error")
@ApiResponse(responseCode = "404", description = "Tenant not found")
@ApiResponse(responseCode = "403", description = "Forbidden")
@ApiResponse(responseCode = "401", description = "Unauthorized")
public @interface CommonApiResponse {
}
