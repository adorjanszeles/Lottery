alter table WEEKLY_DRAW rename column FIRST to FIRST_OLD;

alter table WEEKLY_DRAW add FIRST NUMBER(20);

alter table WEEKLY_DRAW drop column FIRST_OLD;


alter table WEEKLY_DRAW rename column SECOND to SECOND_OLD;

alter table WEEKLY_DRAW add SECOND NUMBER(20);

alter table WEEKLY_DRAW drop column SECOND_OLD;


alter table WEEKLY_DRAW rename column THIRD to THIRD_OLD;

alter table WEEKLY_DRAW add THIRD NUMBER(20);

alter table WEEKLY_DRAW drop column THIRD_OLD;


alter table WEEKLY_DRAW rename column FOURTH to FOURTH_OLD;

alter table WEEKLY_DRAW add FOURTH NUMBER(20);

alter table WEEKLY_DRAW drop column FOURTH_OLD;


alter table WEEKLY_DRAW rename column FIFTH to FIFTH_OLD;

alter table WEEKLY_DRAW add FIFTH NUMBER(20);

alter table WEEKLY_DRAW drop column FIFTH_OLD;