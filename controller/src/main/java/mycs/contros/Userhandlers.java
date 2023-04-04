package mycs.contros;



import mycs.en.Gs;
import mycs.en.User;
import mycs.ser.Gsservice;
import mycs.ser.Userservice;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Transactional
@Controller
public class Userhandlers {




	@Autowired
	private Userservice userdao;

	@Autowired
	private Gsservice gsDao;








	@RequestMapping(value="/query",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String query(Map<String, Object> map,Locale locale, String pageNo, String pageSize) {



		if( pageNo == null || pageNo.equals("")  ){
			pageNo = "0";
		}

		if(pageSize == null || pageSize.equals("")  ){
			pageSize = "3";
		}

		map.put("sizes",Integer.valueOf(pageSize));

		 map.put("querys",userdao.queryUser(Integer.valueOf(pageNo),Integer.valueOf(pageSize)));

		map.put("limits",Integer.valueOf(pageNo));

		map.put("sum",userdao.sums(Integer.valueOf(pageSize)));


		return "qu";

	}

	@RequestMapping(value="/xiaye",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String xiaye(Map<String, Object> map,Locale locale, String pageNo, String pageSize) {

		map.put("sizes",Integer.valueOf(pageSize));
		map.put("limits",Integer.valueOf(pageNo)+1);
		map.put("querys",userdao.queryUser(Integer.valueOf(pageNo)+1,Integer.valueOf(pageSize)));

		map.put("sum",userdao.sums(Integer.valueOf(pageSize)));



		return "qu";

	}

	@RequestMapping(value="/shangye",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String shangye(Map<String, Object> map,Locale locale,   String pageNo, String pageSize) {

		map.put("sizes",Integer.valueOf(pageSize));
		map.put("limits",Integer.valueOf(pageNo)-1);
		map.put("querys",userdao.queryUser(Integer.valueOf(pageNo)-1,Integer.valueOf(pageSize)));

		map.put("sum",userdao.sums(Integer.valueOf(pageSize)));





		return "qu";

	}
	@RequestMapping(value="/weiye",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String weiye(Map<String, Object> map,Locale locale, String pageNo, String pageSize) {

		map.put("sizes",Integer.valueOf(pageSize));
		map.put("limits",Integer.valueOf(pageNo)-1); //
		map.put("querys",userdao.queryUser(Integer.valueOf(pageNo)-1,Integer.valueOf(pageSize)));

		map.put("sum",userdao.sums(Integer.valueOf(pageSize)));



		return "qu";

	}
//
//
//
//
//
//
//
	@RequestMapping("types")
	public String locale(Locale locale,Map<String, String> map) {
		System.out.println(locale.toString());
		map.put("qqqq", locale.toString());
		return "forward:query";
	}
//	
//	
	@RequestMapping("/eupload")
	public String eupload(@RequestParam("file1") MultipartFile[] multipartFiles) throws IOException {
		
		for (MultipartFile multipartFile : multipartFiles) {
			
		
		String name=multipartFile.getOriginalFilename();
		
	FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\win10\\Desktop\\�ļ�����\\testfile\\"+name);	
		
	InputStream inputStream =multipartFile.getInputStream();	
	
	int len;
	byte[] b =new byte[1024];
	while((len= inputStream.read(b))!=-1) {
		fileOutputStream.write(b,0,len);
	}
	inputStream.close();
	fileOutputStream.close();
	
		}
		return "redirect:query";
	}
//	
//	
//	@Autowired
//	private ResourceBundleMessageSource bundleLocator;
	
	
	

	public synchronized String getName(String type) {
		return System.currentTimeMillis() + "." + type;
	}
//		
//	
//	
//	
//	
//	
//	
// 
		@RequestMapping(value="/emp",method=RequestMethod.POST)
		public String addsave(@Validated @ModelAttribute(value="as") User user, BindingResult bindingResult, @RequestParam("fileHead") MultipartFile multipartFile, Map<String, Object> map) throws Exception {
			
			if (bindingResult.getErrorCount() > 0) {
				List<FieldError> list = bindingResult.getFieldErrors();

				for (FieldError fieldError : list) {
					System.out.println(fieldError.getDefaultMessage());

				}
			

				Map<String, String> xingbei = new HashMap();
				xingbei.put("0", " 男 ");
				xingbei.put("1", "女");

				map.put("xingbei", xingbei);

				List<Gs> li = gsDao.qugs();

				map.put("yg", li);

				return "add";
				
				
				
			}
			
			String name=multipartFile.getOriginalFilename();
			String strs[] = name.split("\\.");
			String type = strs[strs.length-1];
			
			String path = "D:\\Java_Test\\"+getName(type);
			
			
			
			FileOutputStream fileOutputStream = new FileOutputStream(path);	
				
			InputStream inputStream =multipartFile.getInputStream();	
			
			int len;
			byte[] b =new byte[1024];
			while((len= inputStream.read(b))!=-1) {
				fileOutputStream.write(b,0,len);
			}
			inputStream.close();
			fileOutputStream.close();
			
			
			
			
			user.setHead(path);
		 
			userdao.add(user);
			//System.out.println(user);
			return "redirect:/query";
		}
//		
//	
//
//		
//		
//		让图片显示
		@RequestMapping("/xiazaizai")
		public void xiazai1(HttpServletResponse response,@RequestParam("path")String path) throws Exception {
			
			
			
			if(path!=null && path.equals(path)) {
			String realPath=path;
			
			
			File file= new File(path);
			
			FileInputStream is = new FileInputStream(realPath);	
				
			  response.setContentType("text/plain;charset=UTF-8");	
			  response.setHeader("Content-Disposition","attachment/fileName="+file.getName());
		
			 ServletOutputStream os = response.getOutputStream();
			     
			  
			  int len;
			byte[] b =new byte[1024];
			while(true) {
				len =is.read(b);
				if(len==-1)break;
				os.write(b,0,len);
			} 
			os.close();
			is.close();
			}
		
		
		}
//		
//		
//		
//		
//		
//
 
//		
//
		@RequestMapping(value="/emp",method=RequestMethod.GET)
		public String add(Map<String,Object> map) {
			
			Map<String,String> xingbei=new HashMap();
			xingbei.put("0", "男" );
			xingbei.put("1","女");
		
			map.put("xingbei", xingbei);
			
 
			
			List<Gs> li=gsDao.qugs();
			
			map.put("yg",li);
			
		
			map.put("as",new User());
			return "add";
		}

//	 
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="id")int id,@RequestParam("pageNo")String pageNo,@RequestParam("pageSize")String pageSize) {
		int pageno = Integer.valueOf(pageNo) ;
		int pagesize = Integer.valueOf(pageSize);

		userdao.delete(id);


		int sum = userdao.qushangye(pageno,pagesize);
 	return "redirect:/query"+"?pageNo="+sum+"&pageSize="+pagesize;
//		return "redirect:/query";
	}

	@RequestMapping(value="/emp/{id}",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String quid(@PathVariable("id")int id,Map<String,Object> map) {

		User user=	userdao.queryid(id);
		System.out.println(user);
		map.put("as",user);
		
		
		Map<String,String> xingbei=new HashMap();
		xingbei.put("0", "男" );
		xingbei.put("1","女");
		map.put("xingbei", xingbei);
	    
		List<Gs> li=gsDao.qugs();
		 
		
		map.put("yg",li);
		
 
		
		return "add";
	}
//	
//	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String updatesave(User user, @RequestParam(value="fileHead")MultipartFile  multipartFile) throws Exception {
		 
		String name=multipartFile.getOriginalFilename();
		String strs[] = name.split("\\.");
		String type = strs[strs.length-1];
		
		String path = "D:\\Java_Test\\"+getName(type);
		
		
		
		FileOutputStream fileOutputStream = new FileOutputStream(path);	
			
		InputStream inputStream =multipartFile.getInputStream();	
		
		int len;
		byte[] b =new byte[1024];
		while((len= inputStream.read(b))!=-1) {
			fileOutputStream.write(b,0,len);
		}
		inputStream.close();
		fileOutputStream.close();
		
		
		
		
		user.setHead(path);
		userdao.update(user);
		return "redirect:/query";
	}
//	
//	
//	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map){
		if(id != null){
			map.put("user",userdao.queryid(id));
		}
	}
//	
//


	@RequestMapping(value="qws",method = RequestMethod.POST)
	public String Exces() {
		
		return "dlexcels";
	}



	@RequestMapping(value = "daochu", method = RequestMethod.POST)
	public String df(HttpServletResponse response) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("user");

		sheet.setColumnWidth(0, 10 * 256);
		sheet.setColumnWidth(1, 10 * 256);
		sheet.setColumnWidth(2, 15 * 256);
		sheet.setColumnWidth(3, 15 * 256);
		sheet.setColumnWidth(4, 20 * 256);

		XSSFRow row = sheet.createRow(0);

		XSSFCell cell = row.createCell(0);
		XSSFCell cell1 = row.createCell(1);
		XSSFCell cell2 = row.createCell(2);
		XSSFCell cell3 = row.createCell(3);
		XSSFCell cell4 = row.createCell(4);
		XSSFCell cell5 = row.createCell(5);
		XSSFCell cell6 = row.createCell(6);
		XSSFCell cell7 = row.createCell(7);

		cell.setCellValue("ID");
		cell1.setCellValue("hand");
		cell2.setCellValue("name");
		cell3.setCellValue("sex");
		cell4.setCellValue("addr");
		cell5.setCellValue("password");
		cell6.setCellValue("birth");
		cell7.setCellValue("gs_id");
		List<User> list = userdao.querysy();

		for (int i = 0; i < list.size(); i++) {

			XSSFRow biao = sheet.createRow(i + 1);

			XSSFCell cell10 = biao.createCell(0);
			XSSFCell cell11 = biao.createCell(1);
			XSSFCell cell22 = biao.createCell(2);
			XSSFCell cell33 = biao.createCell(3);
			XSSFCell cell44 = biao.createCell(4);
			XSSFCell cell55 = biao.createCell(5);
			XSSFCell cell66 = biao.createCell(6);
			XSSFCell cell77 = biao.createCell(7);

			User bo = list.get(i);
			Gs gs=new Gs();

			cell10.setCellValue(bo.getId());
			cell11.setCellValue(bo.getHead());
			cell22.setCellValue(bo.getName());
			cell33.setCellValue(bo.getSex());
			cell44.setCellValue(bo.getAddr());
			cell55.setCellValue(bo.getPassword());
			cell66.setCellValue(bo.getBirth());

			cell77.setCellValue(bo.getGs().getSid());

		}


		String as = "D:\\Java_Test\\cs.xlsx";

		FileOutputStream fileOutputStream = new FileOutputStream(as);
		workbook.write(fileOutputStream);
		fileOutputStream.close();


		return "redirect:/query";

	}




	//导入页面 数据库
	@RequestMapping(value="ru",method = RequestMethod.POST)
	public String ru() throws Exception{


		InputStream in = new FileInputStream("D:\\Java_Test\\cs.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(in);

		int wo = workbook.getNumberOfSheets();

		for (int i = 0; i < wo; i++) {
			XSSFSheet sheets = workbook.getSheetAt(i);

			if (sheets != null) {
				List<User> list=new ArrayList<User>();
				int rows = sheets.getLastRowNum();

				for (int f = 1; f <= rows; f++) {

					XSSFRow row = sheets.getRow(f);

					if (row != null) {


						XSSFCell cell2=row.getCell(0);
						XSSFCell cell3=row.getCell(1);
						XSSFCell cell4=row.getCell(2);
						XSSFCell cell5=row.getCell(3);
						XSSFCell cell6=row.getCell(4);
						XSSFCell cell7=row.getCell(5);
						XSSFCell cell8=row.getCell(6);

//						User user =new User();

						User user =new User();
						Gs gs=new Gs();

						user.setHead(getCellValue(cell2));
						user.setName(getCellValue(cell3));
						user.setSex(getCellValue(cell4));
						user.setAddr(getCellValue(cell5));
						user.setPassword(getCellValue(cell6));

						Date setupTime = HSSFDateUtil.getJavaDate(Double.parseDouble(getCellValue(cell7)));
						user.setBirth(setupTime);

						gs.setSid(Integer.valueOf(getCellValue(cell8)));
						user.setGs(gs);


						list.add(user);

					}

				}


				userdao.addlist(list);
			}

		}
		return "redirect:/query";

	}


  //选择导入
	@RequestMapping(value="daorus",method = RequestMethod.POST)
	public String ru(@RequestParam("daoru") MultipartFile file) throws Exception{

		String flname = file.getOriginalFilename();

		InputStream in = new FileInputStream("D:\\Java_Test\\"+flname);

		XSSFWorkbook workbook = new XSSFWorkbook(in);

		int wo = workbook.getNumberOfSheets();

		for (int i = 0; i < wo; i++) {
			XSSFSheet sheets = workbook.getSheetAt(i);

			if (sheets != null) {
				List<User> list=new ArrayList<User>();
				int rows = sheets.getLastRowNum();

				for (int f = 1; f <= rows; f++) {

					XSSFRow row = sheets.getRow(f);

					if (row != null) {


						XSSFCell cell2=row.getCell(0);
						XSSFCell cell3=row.getCell(1);
						XSSFCell cell4=row.getCell(2);
						XSSFCell cell5=row.getCell(3);
						XSSFCell cell6=row.getCell(4);
						XSSFCell cell7=row.getCell(5);
						XSSFCell cell8=row.getCell(6);

						User user =new User();
						Gs gs=new Gs();

						user.setHead(getCellValue(cell2));
						user.setName(getCellValue(cell3));
						user.setSex(getCellValue(cell4));
						user.setAddr(getCellValue(cell5));
						user.setPassword(getCellValue(cell6));

						Date setupTime = HSSFDateUtil.getJavaDate(Double.parseDouble(getCellValue(cell7)));
						user.setBirth(setupTime);

						gs.setSid(Integer.valueOf(getCellValue(cell8)));
						user.setGs(gs);



						list.add(user);

					}

				}

				userdao.addlist(list);
			}

		}
		return "redirect:/query";

	}





	@SuppressWarnings("deprecation")
	public String getCellValue(Cell cell) {


		if (cell == null)
			return "";


		if (cell.getCellTypeEnum() == CellType.STRING) {


			return cell.getStringCellValue();


		} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {


			return String.valueOf(cell.getBooleanCellValue());


		} else if (cell.getCellTypeEnum() == CellType.FORMULA) {


			return cell.getCellFormula();


		} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {


			return String.valueOf(cell.getNumericCellValue());


		}
		return "";
	}
	
	

	
}
