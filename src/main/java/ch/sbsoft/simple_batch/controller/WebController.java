package ch.sbsoft.simple_batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.sbsoft.simple_batch.postgres.PostgresService;
import ch.sbsoft.simple_batch.redis.RedisService;

@Controller
public class WebController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private PostgresService postgresService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("redis", redisService.getAllSimpleRecord());
        model.addAttribute("postgres", postgresService.getAllSimpleRecord());
        return "index";
    }

    @PostMapping("/save")
    public String save(@RequestParam String valore) {
    	if (valore != null && !valore.isEmpty()) {
            redisService.saveSimpleRecord(valore);
    	}
        return "redirect:/";
    }

    @GetMapping("/move-to-postgres")
    public String moveFromRedisToPostgres(Model model) {
    	String simpleRecord = redisService.getSimpleRecord();
    	if (simpleRecord != null) {
    		postgresService.saveSimpleRecord(simpleRecord);
    	}
        return "redirect:/";
    }

    @GetMapping("/move-to-redis")
    public String moveFromPostgresToRedis(Model model) {
    	String simpleRecord = postgresService.getSimpleRecord();
    	if (simpleRecord != null) {
        	redisService.saveSimpleRecord(simpleRecord);
    	}
        return "redirect:/";
    }

}
