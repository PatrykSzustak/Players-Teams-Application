package com.kchmielewski.sda.java.spring01java.web;

import com.kchmielewski.sda.java.spring01java.model.Player;
import com.kchmielewski.sda.java.spring01java.service.PlayerService;
import com.kchmielewski.sda.java.spring01java.session.PlayerSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("playersPage")
@SessionAttributes("playerSession")
public class PlayerController {

    private final PlayerService playerService;
    private final MessageSource messageSource;

    public PlayerController(MessageSource messageSource, ApplicationContext context)
    {
        this.playerService = context.getBean(PlayerService.class);
        this.messageSource = messageSource;
    }


    @ModelAttribute
    public Player defaultPlayer() {

        return new Player("Fill.my.name...", "...and.my.surname!");
    }

    @ModelAttribute
    public PlayerSession session() {
        return new PlayerSession();
    }


    @PostMapping("")
    public String handleForm(@Valid Player player, BindingResult bindingResult, Model model,
                             @ModelAttribute PlayerSession session, @RequestParam(defaultValue = "") String removeId,
                             @RequestParam(defaultValue = "") String editId) {
        if (!editId.isEmpty()) {
            player.setId(Integer.valueOf(editId));
            return edit(player, bindingResult, model);
        } else if (!removeId.isEmpty()) {
            player.setId(Integer.valueOf(removeId));
            return remove(player.getId(), model);
        }
        return add(player, bindingResult, model, session);
    }

    @GetMapping("")
    public String display(Model model) {
        model.addAttribute("players", playerService.all());

        return "playersPage";
    }

    @GetMapping("/range/{from}/{to}")
    public String displayRange(@PathVariable int from, @PathVariable int to, Model model) {
        model.addAttribute("players2", playerService.findRangePlayers(from, to));
        return "playersPage";
    }

    @GetMapping("/get/{specific}")
    public String displayGet(@PathVariable int specific, Model model) {
        model.addAttribute("player1", playerService.findSpecific(specific));
        return "playersPage";
    }

    private String edit(Player player, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("player", player);
            return "playerEdit";
        }
        playerService.edit(player);
        model.addAttribute("players", playerService.all());
        return "redirect:/playersPage";
    }

    @GetMapping("edit/{playerId}")
    public String edit(Model model, @PathVariable Integer playerId) {
        Optional<Player> player = playerService.findOne(playerId);
        if (player.isPresent()) {
            model.addAttribute("player", player.get());
            return "playerEdit";
        }
        return "playersPage";
    }

    private String add(@Valid Player player, BindingResult bindingResult, Model model, PlayerSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("players", playerService.all());
            return "/playersPage";
        }
        playerService.add(player);
        session.setCounter(session.getCounter() + 1);
        session.setMostRecentPlayer(player);
        model.addAttribute("players", playerService.all());

        return "redirect:/playersPage";
    }

    public String remove(Integer playerId, Model model) {
        playerService.remove(playerId);
        model.addAttribute("players", playerService.all());

        return "redirect:playersPage";
    }

    @ExceptionHandler(Exception.class)
    public String error(Exception exception, Model model, Locale locale) {
        model.addAttribute("exception", exception);
        model.addAttribute("message", messageSource.getMessage("error.message",
                null, locale));
        return "errors";
    }


}
