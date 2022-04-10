package com.cognizant.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cognizant.portal.client.AuthClient;
import com.cognizant.portal.client.MemberClient;
import com.cognizant.portal.dto.AuthenticationResponseDTO;
import com.cognizant.portal.dto.ClaimStatusDTO;
import com.cognizant.portal.dto.ValidatingDTO;
import com.cognizant.portal.model.Bills;
import com.cognizant.portal.model.ClaimDetails;
import com.cognizant.portal.model.LoginModel;
import com.cognizant.portal.service.LoginService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Controller

@Slf4j
public class MemberController {

	@Autowired
	LoginService loginService;

	@Autowired
	AuthClient authclient;

	@Autowired
	MemberClient memberClient;

	@GetMapping("/login")
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("model", new LoginModel());
		return mv;
	}

	@GetMapping("/home")
	public ModelAndView showHome(HttpSession session) {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("model", new LoginModel());
		try {
			ValidatingDTO valid = authclient.getsValidity(session.getAttribute("token").toString());
			if(valid.isValidStatus()) {
				return mv;				
			}
			log.info("Checking valid status: " + valid);
			return new ModelAndView("error");
			}
		catch (Exception e) {
			return new ModelAndView("error");
			}
	}

	@PostMapping("/login")
	public ModelAndView performLogin(@Valid @ModelAttribute("model") LoginModel model, HttpSession session,
			BindingResult result, HttpServletRequest request) throws FeignException {
		ModelAndView mv = new ModelAndView("login");
		System.out.println(result);

		log.info(" Before Token generation");
		AuthenticationResponseDTO token = null;
		try {
			token = loginService.login(model);
		} catch (Exception e) {
			log.info("Exception");
			mv.addObject("error", "Invalid Credentials");
			return mv;
		}

		request.getSession().setAttribute("token", "Bearer " + token.getJwtAuthToken());
		request.getSession().setAttribute("user", model.getUsername());

		log.info(" After Token generation");
		
		log.info(model.getUsername());

		log.info(token.getJwtAuthToken());

		log.info("Dsiplaying User Page");
		return new ModelAndView(new RedirectView("home"));

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		ValidatingDTO valid = authclient.getsValidity(session.getAttribute("token").toString());
//		valid.setValidStatus(false);
		request.getSession().invalidate();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
		
		return "redirect:/login";
	}

	@RequestMapping(value = "/showinputbill", method = RequestMethod.GET)
	public String showInputBillPage(HttpSession session) {
		try {
			ValidatingDTO valid = authclient.getsValidity(session.getAttribute("token").toString());
			if(valid.isValidStatus()) {
				return "input-viewbills";				
			}
			else {
			log.info("Checking valid status: " + valid);
			return "error";
			}
			}
		catch (Exception e) {
			return "error";
			}
	}

	@RequestMapping(value = "/showbills", method = RequestMethod.POST)
	public String showBillStatus(@RequestParam("memberId") String memberId, HttpServletRequest request, ModelMap model)
			throws FeignException {
		String token = (String) request.getSession().getAttribute("token");
		Bills bills = memberClient.getBills(memberId, token).getBody();
		log.info("Displaying Bills");
		model.addAttribute("bills", bills);
		return "viewbills";
	}

	@RequestMapping(value = "/showinputclaimstatus", method = RequestMethod.GET)
	public String showInputClaimStatusPage(HttpSession session) {
		try {
			ValidatingDTO valid = authclient.getsValidity(session.getAttribute("token").toString());
			if(valid.isValidStatus()) {
				return "input-claim";				
			}
			else {
			log.info("Checking valid status: " + valid);
			return "error";
			}
			}
		catch (Exception e) {
			return "error";
			}
	}

	@RequestMapping(value = "/showclaimstatus", method = RequestMethod.POST)
	public String showClaimStatus(@RequestParam("claimId") String claimId, HttpServletRequest request, ModelMap model)
			throws FeignException {
		String token = (String) request.getSession().getAttribute("token");
		ClaimStatusDTO claimstatusdto = memberClient.returnClaimStatus(claimId, token).getBody();
		log.info("Displaying Claim Status");
		model.addAttribute("claimstatusdto", claimstatusdto);
		return "viewclaimstatus";
	}

	@RequestMapping(value = "/showclaim", method = RequestMethod.GET)
	public ModelAndView showSubmitClaimPage(HttpSession session) {
		try {
			ValidatingDTO valid = authclient.getsValidity(session.getAttribute("token").toString());
			if(valid.isValidStatus()) {
		ModelAndView mav = new ModelAndView("submitclaim");
		mav.addObject("claimDetails", new ClaimDetails());
		return mav;
			}
			else {
			log.info("Checking valid status: " + valid);
			return new ModelAndView("error");
			}
			}
		catch (Exception e) {
			return new ModelAndView("error");
			}
	}

	@RequestMapping(value = "/submitclaim", method = RequestMethod.POST)
	public String viewSubmittedClaim(@ModelAttribute("claimDetails") ClaimDetails claim, ModelMap model,
			BindingResult result, HttpServletRequest request) throws FeignException {
		if (result.hasErrors()) {
			return "submitclaim";
		}
		String token = (String) request.getSession().getAttribute("token");
		ClaimStatusDTO claimstatusdto = memberClient.returnClaimStatusOnUpdate(claim, token).getBody();
		log.info("Checking value of claimstatusdto: " + claimstatusdto);
		model.addAttribute("claimstatusdto", claimstatusdto);
		return "submit-claim-notification";
	}

}