package demo.shopping.service.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import demo.shopping.AppSettings;
import demo.shopping.po.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import demo.shopping.dao.AdminGoodsDao;
import demo.shopping.po.Goods;
import demo.shopping.util.MyUtil;
import org.springframework.web.bind.annotation.ResponseBody;

@Service("adminGoodsService")
@Transactional
public class AdminGoodsServiceImpl implements AdminGoodsService{
	@Autowired
	private AdminGoodsDao adminGoodsDao;
	@Autowired
	private AppSettings appSettings;

	@Override
	public int addGoods(Goods goods) {
		String newFileName ="";
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
		  int flag;

			  flag=adminGoodsDao.addGoods(goods) ;
				return flag;
	}

	@Override

	public int updateGoods(Goods goods) {
		String newFileName ="";
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

	adminGoodsDao.updateGoodsById(goods) ;
	return 1;
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
	public List<GoodsType> getGoodsType() {
		//查找所有的商品类型
		List<GoodsType> list=adminGoodsDao.getAllGoodsType();

		return list;
	}

	@Override
	public List<Goods> selectGoods( Integer pageCur, String act) {
		//一次就查询五条数据

	if (pageCur==null)pageCur=0;
		List<Goods> allGoods = adminGoodsDao.selectAllGoodsByPage((pageCur-1)*5,5);

		return allGoods;

	}

	@Override
	public Goods selectAGoods( Integer id) {

		Goods agoods = adminGoodsDao.selectGoodsById(id);

		return agoods;

	}

	@Override
	public int deleteGoods(Integer[] ids) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			list.add(ids[i]);
		}
		int flag;
		flag= adminGoodsDao.deleteGoods(list);

		return flag;

	}

	@Override
	public int deleteAGoods(Integer id) {
		int flag;
		flag=adminGoodsDao.deleteAGoods(id);

	   return flag;

	}

	@Override
	public String imagePath() {
		return appSettings.getLogoPath();
	}
}
