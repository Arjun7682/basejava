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
