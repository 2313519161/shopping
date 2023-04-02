package demo.shopping.service.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import demo.shopping.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminGoodsDao;
import demo.shopping.po.Goods;
import demo.shopping.util.MyUtil;
@Service("adminGoodsService")
@Transactional
public class AdminGoodsServiceImpl implements AdminGoodsService{
	@Autowired
	private AdminGoodsDao adminGoodsDao;
	@Autowired
	private AppSettings appSettings;

	@Override
	public String addOrUpdateGoods(Goods goods, HttpServletRequest request, String updateAct) {
		String newFileName = "";
		String fileName = goods.getLogoImage().getOriginalFilename(); 

		if(fileName.length() > 0){
			String realpath = appSettings.getLogoPath();
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			newFileName = MyUtil.getStringID() + fileType;
			goods.setGpicture(newFileName);
			File targetFile = new File(realpath, newFileName); 
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } 

	        try {   
	        	goods.getLogoImage().transferTo(targetFile);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}

		if("update".equals(updateAct)){
	       if(adminGoodsDao.updateGoodsById(goods) > 0){
	        	return "forward:/adminGoods/selectGoods?act=updateSelect";
	        }else{
	        	return "/adminGoods/updateAgoods";
	       }
		}else{
			if(adminGoodsDao.addGoods(goods) > 0){
				return "forward:/adminGoods/selectGoods";
			}else{
				return "card/addCard";
			}
		}
	}

	@Override
	public int CountPage() {
		//统计有多少页数据
		List<Goods> allGoods = adminGoodsDao.selectGoods();

		return (int)Math.ceil(allGoods.size()/5.0);
	}

	@Override
	public int CountInfo() {
		//统计有多少条商品数据
		List<Goods> allGoods = adminGoodsDao.selectGoods();

		return allGoods.size();
	}

	@Override
	public List<Goods> selectGoods( Integer pageCur, String act) {
		//一次就查询五条数据

	if (pageCur==null)pageCur=0;
		List<Goods> allGoods = adminGoodsDao.selectAllGoodsByPage((pageCur-1)*5,5);


		return allGoods;

	}

	@Override
	public String selectAGoods(Model model, Integer id, String act) {
		Goods agoods = adminGoodsDao.selectGoodsById(id);
		model.addAttribute("goods", agoods);
		if("updateAgoods".equals(act)){
			return "admin/updateAgoods";
		}
		return "admin/goodsDetail";
	}

	@Override
	public String deleteGoods(Integer[] ids, Model model) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			if(adminGoodsDao.selectCartGoods(ids[i]).size() > 0 ||
					adminGoodsDao.selectFocusGoods(ids[i]).size() > 0 || 
					adminGoodsDao.selectOrderdetailGoods(ids[i]).size() > 0) {
				model.addAttribute("msg", "��Ʒ�й�����������ɾ����");
				return "forward:/adminGoods/selectGoods?act=deleteSelect";
			}
			list.add(ids[i]);
		}
		adminGoodsDao.deleteGoods(list);
		model.addAttribute("msg", "�ɹ�ɾ����Ʒ��");
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}

	@Override
	public String deleteAGoods(Integer id, Model model) {
		if(adminGoodsDao.selectCartGoods(id).size() > 0 ||
				adminGoodsDao.selectFocusGoods(id).size() > 0 || 
				adminGoodsDao.selectOrderdetailGoods(id).size() > 0) {
			model.addAttribute("msg", "��Ʒ�й�����������ɾ����");
			return "forward:/adminGoods/selectGoods?act=deleteSelect";
		}
		adminGoodsDao.deleteAGoods(id);
		model.addAttribute("msg", "�ɹ�ɾ����Ʒ��");
		return "forward:/adminGoods/selectGoods?act=deleteSelect";
	}
}
