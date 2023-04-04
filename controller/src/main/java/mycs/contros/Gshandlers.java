package mycs.contros;



import mycs.en.Gs;
import mycs.ser.Gsservice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Locale;
import java.util.Map;


@Controller
@Transactional
public class Gshandlers {




	@Autowired
	private Gsservice gsdao;





	@RequestMapping(value="/gsquery",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String query(Map<String, Object> map,String pageNo, String pageSize) {



		if( pageNo == null || pageNo.equals("")  ){
			pageNo = "0";
		}

		if(pageSize == null || pageSize.equals("")  ){
			pageSize = "3";
		}

		map.put("sizes",Integer.valueOf(pageSize));

		map.put("querys",gsdao.queryGs(Integer.valueOf(pageNo),Integer.valueOf(pageSize)));

		map.put("limits",Integer.valueOf(pageNo));

		map.put("sum",gsdao.gssums(Integer.valueOf(pageSize)));


		return "gs";

	}

	@RequestMapping(value="/gsxiaye",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String xiaye(Map<String, Object> map,Locale locale, String pageNo, String pageSize) {

		map.put("sizes",Integer.valueOf(pageSize));
		map.put("limits",Integer.valueOf(pageNo)+1);
		map.put("querys",gsdao.queryGs(Integer.valueOf(pageNo)+1,Integer.valueOf(pageSize)));

		map.put("sum",gsdao.gssums(Integer.valueOf(pageSize)));



		return "gs";

	}

	@RequestMapping(value="/gsshangye",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String shangye(Map<String, Object> map,Locale locale,   String pageNo, String pageSize) {

		map.put("sizes",Integer.valueOf(pageSize));
		map.put("limits",Integer.valueOf(pageNo)-1);
		map.put("querys",gsdao.queryGs(Integer.valueOf(pageNo)-1,Integer.valueOf(pageSize)));

		map.put("sum",gsdao.gssums(Integer.valueOf(pageSize)));

		return "gs";

	}
	
	
	@RequestMapping(value="/gsweiye",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String weiye(Map<String, Object> map,Locale locale, String pageNo, String pageSize) {

		map.put("sizes",Integer.valueOf(pageSize));
		map.put("limits",Integer.valueOf(pageNo)-1); //
		map.put("querys",gsdao.queryGs(Integer.valueOf(pageNo)-1,Integer.valueOf(pageSize)));

		map.put("sum",gsdao.gssums(Integer.valueOf(pageSize)));



		return "gs";

	}







	@RequestMapping("gstypes")
	public String locale(Locale locale,Map<String, String> map) {
		System.out.println(locale.toString());
		map.put("qqqq", locale.toString());
		return "forward:query";
	}
//
//
//
		@RequestMapping(value="/gs",method=RequestMethod.POST)
		public String addsave(@Validated @ModelAttribute(value="cgs") Gs gs, Map<String, Object> map) throws Exception {


			gsdao.gsadd(gs);


			return "redirect:/gsquery";
		}


		@RequestMapping(value="/gs",method=RequestMethod.GET)
		public String add(Map<String,Object> map) {


			map.put("cgs",new Gs());

			return "gsview";

		}
//
//
//
//
//
//
//
//
//
//
	@RequestMapping(value="/gs/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="id")int id,@RequestParam("pageNo")String pageNo,@RequestParam("pageSize")String pageSize,Map<String,Object> map) {
		int pageno = Integer.valueOf(pageNo) ;
		int pagesize = Integer.valueOf(pageSize);

		Boolean test = gsdao.gsdelete(id);  //获取返回值
		System.out.println("Boolean: " + test);

		if(test==true){

			map.put("test",test);
			int sum = gsdao.gsqushangye(pageno,pagesize);
			return "redirect:/gsquery"+"?pageNo="+sum+"&pageSize="+pagesize;
		}else{

			map.put("test",test);
			return "redirect:/gsquery"+"?pageNo="+pageNo+"&pageSize="+pagesize;

		}


		//这修改


//		return "redirect:/query";
	}
//
//
//
	@RequestMapping(value="/gs/{id}",produces ="text/html;charset=utf-8",method=RequestMethod.GET)
	public String quid(@PathVariable("id")int id,Map<String,Object> map) {

		Gs gs = gsdao.quidgs(id);
		map.put("cgs",gs);


		return "gsview";
	}
//
//
	@RequestMapping(value="/gs",method=RequestMethod.PUT)
	public String updatesave(Gs gs) throws Exception {

		gsdao.gsupdate(gs);


		return "redirect:/gsquery";
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






//	@InitBinder
//	public void testinit() {
//
//		System.out.println("45");
//
//	}


	/*
	 * @RequestMapping(value="bai", method=RequestMethod.GET)
	 *
	 * @ResponseBody //ResponseBody BequestBody ����Ҫ�ɶԳ��� public List<User> bai() {
	 * return userdao.queryUser();
	 *
	 * }
	 */
	@ResponseStatus(value=HttpStatus.GONE,reason = "����������˽ֽǵ���⣬�ڻ�������ץ��ס����һ˲��")
	//д�ڷ����ϲ��ܷ����Ƿ����쳣����󶼻���ʾ@ResponseStatus����
	//���Ƿ�����������ִ�����
	@RequestMapping(value="aaa",method = RequestMethod.GET)
	private String yichang() {
		int a=1/0;

		return "add";

	}


}
