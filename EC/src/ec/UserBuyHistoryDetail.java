package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBuyHistoryBeans;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//追加：userIdで絞り込んだbuyIdから購入詳細情報取得
		try {
			request.setCharacterEncoding("UTF-8");

			String Id = request.getParameter("buy_id"); // buyidを取得
			int buyId = Integer.parseInt(Id);//idをintに直す

			ArrayList<UserBuyHistoryBeans> UserBuyDetailList2 = BuyDetailDAO.getBuyDataBeansListByBuyId2(buyId);
			request.setAttribute("UserBuyDetailList2", UserBuyDetailList2);
			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
