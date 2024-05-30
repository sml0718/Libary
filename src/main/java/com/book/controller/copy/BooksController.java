package com.book.controller.copy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.book.anno.Log;
import com.book.entity.Books;
import com.book.service.BooksService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("books")
public class BooksController {
//git test	
	//hhh
	@Autowired
	private BooksService BooksService;
	
	@Value("${file.upload}")
	private String realpath;
	
	@RequestMapping("search")
	public String searchEmp(String searchName, String searchAuthor, String lowPrice, String topPrice,Model model) {
		
		searchName = (searchName != null) ? searchName.trim() : "";
	    searchAuthor = (searchAuthor != null) ? searchAuthor.trim() : "";
	    // 检查参数是否为 null，如果是 null，就赋予一个空字符串的值，并且避免空指针异常
	    lowPrice = (lowPrice != null) ? lowPrice.trim() : "";
	    topPrice = (topPrice != null) ? topPrice.trim() : "";
	    
		log.info("本の名前:{},作家:{},最低値段:{},最高値段:{},",searchName,searchAuthor,lowPrice,topPrice);
		
		List<Books> books=BooksService.seachBook(searchName,searchAuthor,lowPrice,topPrice);
		
		model.addAttribute("BooksList",books);
		
		return "emplist";
	}
	@Log
	@RequestMapping("delete")
	public String delete(Integer id) {
		log.info("削除の社員ID:{}",id);
		
		String photo = BooksService.findById(id).getPhoto();
		File file=new File(realpath,photo);
		if(file.exists())file.delete();
		
		BooksService.delete(id);
		return "redirect:/books/lists";
		
	}
	@Log
	@RequestMapping("update")
	public String update(Books books,MultipartFile img) throws IllegalStateException, IOException {
		log.info("更新した本：{},本の名前:{},作家:{},値段:{}",books.getId(),books.getName(),books.getAuthor(),books.getPrice());
		
		boolean notempty = !img.isEmpty();
		log.info("写真更新するかどうが:{}",notempty);
		
		if(notempty) {
			String oldphoto=BooksService.findById(books.getId()).getPhoto();
			File file=new File(realpath,oldphoto);
			if(file.exists()) {
				file.delete();
			}
			String originalFilename = img.getOriginalFilename();
			
			String newName = uploadPhoto(img,originalFilename);
			
			books.setPhoto(newName);
		}

		BooksService.update(books);
		return "redirect:/books/lists";
		
	}
	private String uploadPhoto(MultipartFile img,String originalFilename) throws IllegalStateException, IOException {
		
		String fileNamePrefix = originalFilename.substring(0,originalFilename.lastIndexOf("."));
		String fileNamesufix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		
		String newName=fileNamePrefix+fileNamesufix+originalFilename.substring(originalFilename.lastIndexOf("."));

		log.info("新しいファイルの名:{}",newName);
		
		img.transferTo(new File(realpath,newName));
		
		
		return newName;
		
	}
	
	
	@RequestMapping("detail")
	public String detail(Integer id ,Model model) {
		log.info("更新ID:{}",id);
		
		Books books=BooksService.findById(id);
		
		model.addAttribute("books",books);
		
		return"updateEmp";
		
	}
	@Log
	@RequestMapping("save")
	public String save(Books books,MultipartFile img) throws IllegalStateException, IOException {
//		log.info("本の名前:{},作家:{},値段:{}",books.getName(),books.getauthor(),books.getprice());
		
		String originalFilename = img.getOriginalFilename();
		log.info("ファイルの名:{},ファイルのサイズ：{},アップロードあど:{}",originalFilename,img.getSize()
				,realpath);
		
		
		String newName = uploadPhoto(img,originalFilename);
		log.info("新しいファイルの名:{}",newName);
		
		
		
		
		books.setPhoto(newName);
		
		BooksService.save(books);
	
		
		return "redirect:/books/lists";
	}
	
	@RequestMapping("lists")
	public String lists(Model model) {
		
		log.info("全部社員を照会する");
		
		List<Books> booksList =BooksService.lists();
		
		model.addAttribute("BooksList",booksList);
		return "emplist";
		
	}

}
