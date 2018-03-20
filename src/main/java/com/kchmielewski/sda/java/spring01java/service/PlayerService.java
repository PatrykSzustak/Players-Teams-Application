package com.kchmielewski.sda.java.spring01java.service;

import com.kchmielewski.sda.java.spring01java.data.entity.PlayerEntity;
import com.kchmielewski.sda.java.spring01java.data.entity.TeamEntity;
import com.kchmielewski.sda.java.spring01java.data.repository.PlayerRepository;
import com.kchmielewski.sda.java.spring01java.data.repository.TeamRepository;
import com.kchmielewski.sda.java.spring01java.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }


    private final List<Player> players = new ArrayList<>();

    public List<Player> all() {
        List<PlayerEntity> list = repository.findAll();
        List<Player> list1 = list.stream().map(p -> toPlayerDto(p)).collect(Collectors.toList());

        return list1;
    }

    public Optional<Player> findOne(Integer playerId) {
        return repository.findById(playerId).map(this::toPlayerDto);
    }

    public List<PlayerEntity> findAdamLallana(){
        return repository.findLallana();
    }

    public List<PlayerEntity> findBySurnameStartingWith(String surname){
        return repository.findBySurnameStartingWith(surname);
    }


    public void add(Player player) {
        checkNotNull(player, "Player cannot be null");
        repository.save(toEntity(player));
    }

    public void remove(Integer playerId) {
        checkNotNull(playerId, "Player cannot be null");
        repository.deleteById(playerId);
    }

    public void edit(Player player) {
        checkNotNull(player, "Player cannot be null");
        checkArgument(player.getId() != null, "If player is to be edited, it needs it's id to be set.");

        Optional<TeamEntity> team = teamRepository.findByName(player.getTeamName());
        PlayerEntity entity = toEntity(player);
        team.ifPresent(t -> entity.setTeam(t));
        if (!team.isPresent()) {
            TeamEntity teamEntity = new TeamEntity(player.getTeamName());
            teamRepository.save(teamEntity);
            entity.setTeam(teamEntity);
        }
        repository.save(entity);
    }

    public Player toPlayerDto(PlayerEntity entity) {
        return new Player(entity.getId(),entity.getName(), entity.getSurname(),
        entity.getTeam() == null ? "" : entity.getTeam().getName());
    }

    public PlayerEntity toEntity(Player player) {
        return new PlayerEntity(player.getId(),player.getName(), player.getSurname());
    }


    private List<Player> toPlayerDto(List<PlayerEntity> entities) {
        return entities.stream().map(this::toPlayerDto).collect(Collectors.toList());
    }

    public Player findSpecific(int specific) {
        return players.get(specific);
    }

    public List<Player> findRangePlayers(int from, int to) {
        return players.subList(from, to);
    }


//    @Scheduled(fixedRate = 1000)
//    public void showPlayers() {
//        System.out.println(repository.findAll().size());
//    }

}
