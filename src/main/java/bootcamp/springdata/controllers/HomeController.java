package bootcamp.springdata.controllers;

import bootcamp.springdata.model.ApplicationRequest;
import bootcamp.springdata.model.Courses;
import bootcamp.springdata.model.Operators;
import bootcamp.springdata.repository.CoursesRepository;
import bootcamp.springdata.repository.OperatorsRepository;
import bootcamp.springdata.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private OperatorsRepository operatorsRepository;

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        List<ApplicationRequest> applicationRequestList = requestRepository.findAllByOrderByIdAsc();
        model.addAttribute("applicationRequestList", applicationRequestList);
        return "index";
    }

    @GetMapping(value = "/addRequest")
    public String addRequestPage(Model model) {
        List<ApplicationRequest> applicationRequestList = requestRepository.findAllByOrderByIdAsc();
        model.addAttribute("applicationRequestList", applicationRequestList);
        List<Courses> coursesList = coursesRepository.findAll();
        model.addAttribute("coursesList", coursesList);
        return "addRequest";
    }

    @PostMapping(value = "/addRequest")
    public String addRequest(ApplicationRequest applicationRequest) {
        requestRepository.save(applicationRequest);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{reqId}")
    public String details(@PathVariable(name = "reqId") Long reqId,
                          Model model) {
        ApplicationRequest applicationRequest = requestRepository.findById(reqId).orElseThrow();
        model.addAttribute("applicationRequest", applicationRequest);
        List<Operators> operators = operatorsRepository.findAll();
        model.addAttribute("operators", operators);
        return "details";
    }

    @PostMapping(value = "/updateRequest")
    public String updateRequest(@RequestParam(name = "request.id") Long requestId,
                                @RequestParam(name = "op.id") List<Operators> operators,
                                Model model) {
        ApplicationRequest applicationRequest = requestRepository.findById(requestId).orElseThrow();
        if (applicationRequest != null && operators!=null) {
            applicationRequest.setOperators(operators);
            applicationRequest.setHandled(true);
            requestRepository.save(applicationRequest);
        }
        return "redirect:/details/" + applicationRequest.getId();
    }

    @PostMapping(value = "/deleteOperator")
    public String deleteOperator(@RequestParam(name = "request.id") Long requestId,
                                 @RequestParam(name = "operator.id") Long operatorId) {
        ApplicationRequest applicationRequest = requestRepository.findById(requestId).orElseThrow();
        if (applicationRequest != null) {
            Operators operator = operatorsRepository.findById(operatorId).orElseThrow();
            if (operator != null) {
                applicationRequest.getOperators().remove(operator);
                requestRepository.save(applicationRequest);
            }
        }
        return "redirect:/details/" + requestId;
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
