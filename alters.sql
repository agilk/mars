alter table mars_player_mats add cost_steel int not null default 2;
alter table mars_player_mats add cost_titan int not null  default 3;
alter table mars_player_mats add plant_greenery int  not null default 8;
alter table mars_player_mats add heat_temperature int  not null default 8;
alter table mars_player_mats add cost_sp_power int  not null default 11;
alter table mars_player_mats add cost_sp_temp int  not null default 14;
alter table mars_player_mats add cost_sp_ocean int  not null default 18;
alter table mars_player_mats add cost_sp_green int  not null default 23;
alter table mars_player_mats add cost_sp_city int  not null default 25;
alter table mars_player_mats add eff_cost_power_tag int  not null default 0;

alter table mars_player_mats add tag_science int  not null default 0;
alter table mars_player_mats add tag_building int  not null default 0;
alter table mars_player_mats add tag_space int  not null default 0;
alter table mars_player_mats add tag_energy int  not null default 0;
alter table mars_player_mats add tag_earth int  not null default 0;
alter table mars_player_mats add tag_jovian int  not null default 0;
alter table mars_player_mats add tag_microbe int  not null default 0;
alter table mars_player_mats add tag_plant int  not null default 0;
alter table mars_player_mats add tag_animal int  not null default 0;
alter table mars_player_mats add tag_venus int  not null default 0;
alter table mars_player_mats add tag_event int  not null default 0;

alter table mars_cards add eff_cost_steel int default 0;
alter table mars_cards add eff_cost_titan int default 0;

alter table mars_corporations add eff_cost_steel int default 0;
alter table mars_corporations add eff_cost_titan int default 0;
alter table mars_corporations add eff_plants_greenery int default 0;
alter table mars_corporations add eff_cost_power_tag int default 0;

update mars_corporations set eff_plants_greenery=-1 where id=1;
update mars_corporations set eff_cost_titan=1 where id=3;
update mars_corporations set eff_cost_power_tag=-3 where id=4;

update mars_cards set eff_cost_steel=1, eff_cost_titan=1 where cost=9 and card_id=71;

alter table mars_player_mats drop cost_steel ; -- 2;
alter table mars_player_mats drop cost_titan ; -- 3;
alter table mars_player_mats drop plant_greenery ; -- 8;
alter table mars_player_mats drop heat_temperature ; -- 8;
alter table mars_player_mats drop cost_sp_power ; -- 11;
alter table mars_player_mats drop cost_sp_temp ; -- 14;
alter table mars_player_mats drop cost_sp_ocean ; -- 18;
alter table mars_player_mats drop cost_sp_green ; -- 23;
alter table mars_player_mats drop cost_sp_city ; -- 25;
alter table mars_player_mats drop eff_cost_power_tag ; -- 0;


insert into mars_exceptions (message, code) values ("science tags required", upper("TAGS_REQUIRED_science"));
insert into mars_exceptions (message, code) values ("building tags required", upper("TAGS_REQUIRED_building"));
insert into mars_exceptions (message, code) values ("space tags required", upper("TAGS_REQUIRED_space"));
insert into mars_exceptions (message, code) values ("energy tags required", upper("TAGS_REQUIRED_energy"));
insert into mars_exceptions (message, code) values ("earth tags required", upper("TAGS_REQUIRED_earth"));
insert into mars_exceptions (message, code) values ("jovian tags required", upper("TAGS_REQUIRED_jovian"));
insert into mars_exceptions (message, code) values ("microbe tags required", upper("TAGS_REQUIRED_microbe"));
insert into mars_exceptions (message, code) values ("plant tags required", upper("TAGS_REQUIRED_plant"));
insert into mars_exceptions (message, code) values ("animal tags required", upper("TAGS_REQUIRED_animal"));
insert into mars_exceptions (message, code) values ("venus tags required", upper("TAGS_REQUIRED_venus"));
insert into mars_exceptions (message, code) values ("event tags required", upper("TAGS_REQUIRED_event"));

