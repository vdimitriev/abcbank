package mk.dmt.abc.service;

import mk.dmt.abc.entity.CountryEntity;
import mk.dmt.abc.mapper.CountryMapper;
import mk.dmt.abc.model.Country;
import mk.dmt.abc.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountryService {

    private final CountryMapper countryMapper;

    private final CountryRepository countryRepository;

    public CountryService(CountryMapper countryMapper, CountryRepository countryRepository) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
    }

    public void addCountry(Country country) {
        this.countryRepository.save(countryMapper.apply(country));
    }

    public void removeCountry(String code) {
        final Optional<CountryEntity> countryEntityOptional = this.countryRepository.findByCode(code);
        if(countryEntityOptional.isPresent()) {
            CountryEntity countryEntity = countryEntityOptional.get();
            this.countryRepository.delete(countryEntity);
        }

    }
}
