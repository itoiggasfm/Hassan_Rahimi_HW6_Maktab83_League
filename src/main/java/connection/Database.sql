create database league_db;

create table tbl_league
    (
        id int unique primary key,
        name varchar
);

create table tbl_club
(
    id int UNIQUE PRIMARY KEY,
    name varchar,
    league_id int,
        constraint fk_leagueid
            foreign key (league_id)
                references tbl_league(id)
);

create table tbl_match
(
    league_id int,
    id int,
    first_club_view boolean,
    first_club_id int,
    first_leg boolean,
    second_leg boolean,
    first_club_name varchar,
    second_club_name varchar,
    first_club_goals int,
    second_club_goals int,
    first_club_win boolean,
    first_club_draw boolean,
    first_club_lose boolean,
    first_club_point int,
    second_club_win boolean,
    second_club_draw boolean,
    second_club_lose boolean,
    second_club_point int,
        constraint fk_leagueid
            foreign key (league_id)
                references tbl_league(id),
    constraint fk_clubid
        foreign key (first_club_id)
            references tbl_club(id),
    primary key (league_id, id, first_club_id)
);