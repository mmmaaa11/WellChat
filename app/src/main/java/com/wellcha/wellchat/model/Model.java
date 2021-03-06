package com.wellcha.wellchat.model;

import android.content.Context;

import com.wellcha.wellchat.model.bean.UserInfo;
import com.wellcha.wellchat.model.dao.UserAccountDao;
import com.wellcha.wellchat.model.db.DBManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
//数据模型层全局类
public class Model {
    private Context mContext;
    private UserAccountDao userAccountDao;
    private ExecutorService executors= Executors.newCachedThreadPool();
    private static Model model=new Model();

    private DBManager dbManager;
    private Model(){

    }
    //获取单例对象
    public static Model getInstance(){
        return model;
    }
    //初始化的方法
    public void init(Context context){
        mContext=context;

         userAccountDao=new UserAccountDao(mContext);
        // 开启全局监听
        EventListener eventListener = new EventListener(mContext);
    }
    //获取全局线程池对象
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }
    //用户登陆成功后处理方法
    public void loginSuccess(UserInfo account) {

        // 校验
        if(account == null) {
            return;
        }

        if(dbManager != null) {
            dbManager.close();
        }

        dbManager = new DBManager(mContext, account.getName());
    }
    //获取用户账号数据库操作类对象
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }
    public DBManager getDbManager(){
        return dbManager;
    }
}
