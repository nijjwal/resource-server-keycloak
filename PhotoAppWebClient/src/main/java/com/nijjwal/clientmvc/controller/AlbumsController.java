package com.nijjwal.clientmvc.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.nijjwal.clientmvc.response.AlbumRest;

@Controller
public class AlbumsController {
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;

	@Autowired
	WebClient webClient;

	@GetMapping("/albums")
	public String getAlbums(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());

		String url = "http://localhost:8082/albums";

		List<AlbumRest> albums = webClient.get().uri(url).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
				}).block();

		model.addAttribute("albums", albums);

		return "albums";

	}

	@GetMapping(path = "/")
	public String index() {
		return "external";
	}
}
