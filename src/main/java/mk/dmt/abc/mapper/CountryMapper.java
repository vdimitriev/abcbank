package mk.dmt.abc.mapper;

import mk.dmt.abc.entity.CountryEntity;
import mk.dmt.abc.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryEntity apply(Country country) {
        final CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCode(country.getCode());
        countryEntity.setName(country.getName());
        countryEntity.setEnabled(country.getEnabled());
        return countryEntity;
    }
}
