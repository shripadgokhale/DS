package controller;

import model.FileInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import service.FileList;
 
@RestController
public class DirectoryStructureController  {

@Autowired
FileList list;	
	
@RequestMapping(value = {"/fileinfo"}, method=RequestMethod.POST)
 public  ModelAndView showInfo(@RequestParam(value="name", required=true) String name, Model model) {
	FileInfo fileInfo = list.getList(name);
	if(fileInfo == null){
		model.addAttribute("message", "Invalid path");
		return new  ModelAndView("fail");
	}
	model.addAttribute("list", fileInfo);
	return new ModelAndView( "fileinfo");
 }

@RequestMapping(value = {"/fileinfo"}, method=RequestMethod.GET)
public @ResponseBody FileInfo showJsonInfo(@RequestParam(value="name", required=true) String name, Model model) {
	System.out.println("Name :" + name);
	FileInfo fileInfo = list.getList(name);
	return fileInfo;
}


@RequestMapping("/fail")
public ModelAndView fail(Model model){
	model.addAttribute("message", "Login Failed");
	return new ModelAndView("fail");
}

@RequestMapping("/enterfile")
public ModelAndView fileName(){
	return new ModelAndView("enterfile");
}
}