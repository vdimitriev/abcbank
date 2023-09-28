create table if not exists country(
    id                              bigserial                     not null primary key,
    country_name                    text                          not null,
    country_code                    text                          not null,
    enabled                         boolean
);

insert into country(country_name, country_code, enabled) values('Netherlands', 'NL', true);
insert into country(country_name, country_code, enabled) values('Belgium', 'BE', true);
insert into country(country_name, country_code, enabled) values('Germany', 'DE', true);