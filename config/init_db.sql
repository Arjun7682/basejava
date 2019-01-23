CREATE TABLE public.resume
(
  uuid      varchar(36) PRIMARY KEY,
  full_name TEXT NOT NULL
);

CREATE TABLE public.contact
(
  id          serial PRIMARY KEY,
  type        text     NOT NULL,
  value       text     NOT NULL,
  resume_uuid varchar(36) NOT NULL REFERENCES public.resume (uuid) ON DELETE CASCADE
);

CREATE TABLE public.section
(
  id          serial PRIMARY KEY,
  type        text        NOT NULL,
  value       text        NOT NULL,
  resume_uuid varchar(36) NOT NULL REFERENCES public.resume (uuid) ON DELETE CASCADE
);
