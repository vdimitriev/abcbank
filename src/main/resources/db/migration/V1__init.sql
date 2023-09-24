create table if not exists account(
    id                              bigserial                     not null primary key,
    account_id                      text                          not null,
    iban                            text                          not null,
    balance                         numeric(15,6)                 not null,
    account_type                    text                          not null,
    currency                        text                          not null,
    created                         timestamp  with time zone     not null,
    constraint uk_account_account_id unique (account_id),
    constraint uk_account_iban unique (iban)
);

create table if not exists document(
    id                              bigserial                     not null primary key,
    document_id                     text                          not null,
    document_type                   text                          not null,
    document_number                 text                          not null,
    issue_country                   text                          ,
    issue_date                      timestamp  with time zone     ,
    expiry_date                     timestamp  with time zone     ,
    constraint uk_document_document_id unique (document_id)
);

create table if not exists customer(
    id                              bigserial                     not null primary key,
    customer_id                     text                          not null,
    document_id                     bigserial                     not null,
    account_id                      bigserial                     not null,
    full_name                       text                          not null,
    username                        text                          not null,
    address                         text                          ,
    country                         text                          not null,
    password                        text                          not null,
    date_of_birth                   timestamp  with time zone     not null,
    constraint uk_customer_customer_id unique (customer_id),
    constraint fk_customer_account_id foreign key (account_id) references account (id),
    constraint fk_customer_document_id foreign key (document_id) references document (id)
);