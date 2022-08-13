package bootcamp.springdata.controllers;

import bootcamp.springdata.model.ApplicationRequest;
import bootcamp.springdata.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RequestRepository requestRepository;

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        List<ApplicationRequest> applicationRequestList = requestRepository.findAll();
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "index";
    }

    @GetMapping(value = "/addRequest")
    public String addRequestPage(Model model) {
        List<ApplicationRequest> applicationRequestList = requestRepository.findAll();
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "addRequest";
    }

    @PostMapping(value = "/addRequest")
    public String addRequest(ApplicationRequest applicationRequest,
                             Model model) {
        requestRepository.save(applicationRequest);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{reqId}")
    public String details(@PathVariable(name = "reqId") Long reqId,
                          Model model) {
        ApplicationRequest applicationRequest = requestRepository.findById(reqId).orElseThrow();
        model.addAttribute("applicationRequest", applicationRequest);
        return "details";
    }

    @PostMapping(value = "/updateRequest")
    public String updateRequest(@RequestParam(name = "id") Long id) {
        ApplicationRequest applicationRequest = requestRepository.findById(id).orElseThrow();
        applicationRequest.setHandled(true);
        requestRepository.save(applicationRequest);
        return "redirect:/details/" + id;
    }

    @PostMapping(value = "/deleteRequest")
    public String deleteRequest(@RequestParam(name = "id") Long id) {
        requestRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/newRequests")
    public String newRequestsPage(Model model) {
        List<ApplicationRequest> applicationRequestList = requestRepository.findAllByHandled(false);
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "index";
    }

    @GetMapping(value = "/handledRequests")
    public String handledRequests(Model model) {
        List<ApplicationRequest> applicationRequestList = requestRepository.findAllByHandled(true);
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "index";
    }
}
