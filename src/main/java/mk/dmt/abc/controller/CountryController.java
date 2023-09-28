package mk.dmt.abc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mk.dmt.abc.annotation.CommonApiResponse;
import mk.dmt.abc.model.Country;
import mk.dmt.abc.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Add new country.")
    @ApiResponse(responseCode = "201", description = "New country added")
    @CommonApiResponse
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> addCountry(@Valid @RequestBody final Country country) {
        countryService.addCountry(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Delete country.")
    @ApiResponse(responseCode = "200", description = "Deleted existing country")
    @CommonApiResponse
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{countryCode}")
    public HttpStatus delete(@PathVariable String countryCode) {
        countryService.removeCountry(countryCode);
        return HttpStatus.OK;
    }
}
