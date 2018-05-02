package com.revature.rest;

import static com.revature.util.ClientMessageUtil.HERO_NOT_FOUND;
import static com.revature.util.ClientMessageUtil.INVALID_DATA;
import static com.revature.util.ClientMessageUtil.REGISTRATION_SUCCESSFUL;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ajax.ClientMessage;
import com.revature.model.Hero;
import com.revature.service.HeroService;

/**
 * @RequestMapping(value="/register.app", method=RequestMethod.POST)
 * Is the same as we have downstairs.
 * 
 * * path = /something generalizes our URIs a little bit more.
 * ** For this controller, now we have to perform /hero/register for example.
 * * produces tells Spring that we can marshal specific mediatypes.
 * ** if the client now sends us a header saying: Accept="application/xml", he will get XML.
 * 
 * @ResponseBody marshal our POJOS into JSON with Jackson (because we have the dependency).
 * 
 * @RequestBody unmarshals JSON available in the request body into our POJO.
 * 
 * @CrossOrigin is a security feature of the browser. We must allow explicitly each client.
 * [IT'S NOW HANDLED BY ZUUL, If we leave it here, we will have double cross-origin allowance].
 */
@RestController("heroController")
public class HeroControllerAlpha implements HeroController {
	
	@Autowired
	private HeroService heroService;
	
	@PostMapping("/register")
	public ResponseEntity<ClientMessage> registerHero(@RequestBody Hero hero) {
		return (heroService.registerHero(hero)) ? 
				new ResponseEntity<>(REGISTRATION_SUCCESSFUL, HttpStatus.OK) : 
				new ResponseEntity<>(INVALID_DATA, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/find")
	public ResponseEntity<Object> findHero(@RequestBody Hero hero, HttpServletRequest request) {
		Hero foundHero = heroService.getHero(hero.getName());
		
		if(foundHero != null) {
			return new ResponseEntity<>(foundHero, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HERO_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Hero>> findAllHeroes() {
		return new ResponseEntity<>(heroService.getAllHeroes(), HttpStatus.OK);
	}
}
