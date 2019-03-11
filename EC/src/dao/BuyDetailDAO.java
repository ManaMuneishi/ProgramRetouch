package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;
import beans.UserBuyHistoryBeans;

/**
 *
 * @author d-yamaguchi
 *
 */
public class BuyDetailDAO {

	/**
	 * 購入詳細登録処理
	 * @param bddb BuyDetailDataBeans
	 * @throws SQLException
	 * 			呼び出し元にスローさせるため  //ここの意味は?
	 */
	public static void insertBuyDetail(BuyDetailDataBeans bddb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy_detail(buy_id,item_id) VALUES(?,?)");
			st.setInt(1, bddb.getBuyId());
			st.setInt(2, bddb.getItemId());
			st.executeUpdate();
			System.out.println("inserting BuyDetail has been completed");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return {BuyDataDetailBeans} //ここと下と説明の書き方が違うのは?内容は同じリストの値。
	 * @throws SQLException
	 */
	public ArrayList<BuyDetailDataBeans> getBuyDataBeansListByBuyId(int buyId) throws SQLException { //ここ参照
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM t_buy_detail WHERE buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<BuyDetailDataBeans> buyDetailList = new ArrayList<BuyDetailDataBeans>();

			while (rs.next()) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setId(rs.getInt("id"));
				bddb.setBuyId(rs.getInt("buy_id"));
				bddb.setItemId(rs.getInt("item_id"));
				buyDetailList.add(bddb);
			}

			System.out.println("searching BuyDataBeansList by BuyID has been completed");
			return buyDetailList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	 /**
     * 購入IDによる購入詳細情報検索
     * @param buyId
     * @return buyDetailItemList ArrayList<ItemDataBeans>
     *             購入詳細情報のデータを持つJavaBeansのリスト
     * @throws SQLException
     */
	public static ArrayList<ItemDataBeans> getItemDataBeansListByBuyId(int buyId) throws SQLException {//ここ参照
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT m_item.id,"
					+ " m_item.name,"
					+ " m_item.price"
					+ " FROM t_buy_detail"
					+ " JOIN m_item"
					+ " ON t_buy_detail.item_id = m_item.id"
					+ " WHERE t_buy_detail.buy_id = ? ");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<ItemDataBeans> buyDetailItemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));

				buyDetailItemList.add(idb);
			}

			System.out.println("searching ItemDataBeansList by BuyID has been completed");
			return buyDetailItemList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	 /**購入IDによる購入詳細情報検索
     * @param buyId
     * @return BuyAllDetailList ArrayList<UserBuyHistoryBeans>
     *             購入商品名を含む詳細情報のデータを持つJavaBeansのリスト
     * @throws SQLException
     */
	public static ArrayList<UserBuyHistoryBeans> getBuyDataBeansListByBuyId2(int buyId) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT A.user_id, A.total_price, A.delivery_method_id, A.create_date," //ALLで書いた方がいいのか。(内容のかぶるカラムが多いので省きたいと思った)
					+ "B.buy_id, B.item_id, "
					+ "C.name, C.detail, C.price, C.file_name "
					+ "FROM t_buy A INNER JOIN t_buy_detail B "
					+ "ON A.id = B.buy_id "
					+ "INNER JOIN m_item C "
					+ "ON B.item_id = C.id "
					+ "WHERE B.buy_id = ?");

			st.setInt(1, buyId);
			ResultSet rs = st.executeQuery();
			ArrayList<UserBuyHistoryBeans> BuyAllDetailList = new ArrayList<UserBuyHistoryBeans>();

			while (rs.next()) {
				UserBuyHistoryBeans bhb = new UserBuyHistoryBeans();
				bhb.setUserId(rs.getInt("user_id"));
				bhb.setTotalPrice(rs.getInt("total_price"));
				bhb.setDeliveryMethodId(rs.getInt("delivery_method_id"));
				bhb.setCreateDate(rs.getTimestamp("create_date"));
				bhb.setBuyId(rs.getInt("buy_id"));
				bhb.setItemId(rs.getInt("item_id"));
				bhb.setName(rs.getString("name"));
				bhb.setDetail(rs.getString("detail"));
				bhb.setPrice(rs.getInt("price"));
				bhb.setFileName(rs.getString("file_name"));
				BuyAllDetailList.add(bhb);
			}

			System.out.println("searching UserBuyHistoryBeans by buyID has been completed");
			return BuyAllDetailList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}

