CREATE TABLE public.resume
(
  uuid      CHAR(36) PRIMARY KEY,
  full_name TEXT NOT NULL
);

CREATE TABLE public.contact
(
  id          serial PRIMARY KEY,
  type        text     NOT NULL,
  value       text     NOT NULL,
  resume_uuid char(36) NOT NULL REFERENCES public.resume (uuid) ON DELETE CASCADE
);

create unique index contact_uuid_type_index
  on public.contact (resume_uuid, type);

create table section
(
  id          serial      not null
    constraint table_name_pkey
    primary key,
  type        text        not null,
  value       text        not null,
  resume_uuid varchar(36) not null
    constraint table_name_resume_uuid_fk
    references resume
    on delete cascade
);

alter table section
  owner to postgres;

create unique index table_name_id_uindex
  on section (id);
