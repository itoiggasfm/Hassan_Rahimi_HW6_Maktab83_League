create database league_db;

create table tbl_league
    (
        id int unique primary key,
        name varchar
);

create table tbl_club
(
    id int unique primary key,
    name varchar,
    league_id int,
        constraint fk_leagueid
            foreign key (league_id)
                references tbl_league(id)
);

create table tbl_match
(
    id int,
    league_id int,
    club_id int,
    first_club_name varchar,
    second_club_name varchar,
    first_club_goals int,
    second_club_goals int,
    win boolean,
    lose boolean,
    draw boolean,
    point int,
        constraint fk_leagueid
            foreign key (league_id)
                references tbl_league(id),
    constraint fk_clubid
        foreign key (club_id)
            references tbl_club(id),
    primary key (id, league_id)
);