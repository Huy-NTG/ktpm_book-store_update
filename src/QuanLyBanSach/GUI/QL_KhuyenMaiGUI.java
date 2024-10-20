package QuanLyBanSach.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JYearChooser;

import MyCustom.MyDialog;
import MyCustom.XulyInput;
import QuanLyBanSach.BUS.CTKhuyenMaiBUS;
import QuanLyBanSach.BUS.KhuyenMaiBUS;
import QuanLyBanSach.DAO.MyConnect;
import QuanLyBanSach.DTO.CTKhuyenMai;
import QuanLyBanSach.DTO.KhuyenMai;
import QuanLyBanSach.DTO.SanPham;
import QuanLyBanSach.BUS.SanPhamBUS;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QL_KhuyenMaiGUI extends JFrame {
        private XulyInput xulyinput;
	private JPanel contentPane;
        JPanel panel_5;
	private JTextField txt_MaKM;
	private JTextField txt_TenKM;
	private JTable tbl_KM;
	private JTextField txt_MaKMCT;
	private JTextField txt_phanTram;
	private JTextField txt_dieuKien;
	private JTable tbl_CTKM;
	JDateChooser date_BĐ;
	JDateChooser date_KT;
	private KhuyenMaiBUS KMBUS = new KhuyenMaiBUS();
	private CTKhuyenMaiBUS CTKMBUS = new CTKhuyenMaiBUS();
        private SanPhamBUS spBus=new SanPhamBUS();
	DefaultTableModel model_KM = new DefaultTableModel();
	DefaultTableModel model_CTKM = new DefaultTableModel();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	//private JTextField txt_maSP;
	private JButton btn_ThemKM, btn_SuaKM, btn_XoaKM,btn_XoaCTKM,btn_SuaCTKM,btn_ThemCTKM;
        private LocalDate today=LocalDate.now();
        private JComboBox cbx_sp;
         /**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new MyConnect();
//					QL_KhuyenMaiGUI frame = new QL_KhuyenMaiGUI();
//					frame.setVisible(true);
//					frame.loadingKhuyenMai();
//					frame.loadingCTKhuyenMai();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public QL_KhuyenMaiGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1108, 799);
		this.setLocationRelativeTo(null); //hiển thị giữa mà hình
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 63, 674, 638);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Thông tin khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 10, 654, 123);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã khuyến mãi :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 25, 125, 30);
		panel_2.add(lblNewLabel_1);
		
		txt_MaKM = new JTextField();
		txt_MaKM.setBounds(145, 25, 138, 27);
                txt_MaKM.setEditable(false);
		panel_2.add(txt_MaKM);
		txt_MaKM.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên khuyến mãi :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 78, 125, 30);
		panel_2.add(lblNewLabel_1_1);
		
		txt_TenKM = new JTextField();
		txt_TenKM.setColumns(10);
		txt_TenKM.setBounds(145, 78, 240, 27);
		panel_2.add(txt_TenKM);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày BD:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(395, 25, 77, 30);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Ngày KT:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBounds(395, 78, 77, 30);
		panel_2.add(lblNewLabel_1_2_1);
		
		date_BĐ = new JDateChooser();
		date_BĐ.setDateFormatString("dd/MM/yyyy");
		//date_BĐ.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 15));
                date_BĐ.getDateEditor().getUiComponent().setFont(new Font("Tahoma", Font.PLAIN, 15));
		date_BĐ.setBounds(482, 10, 162, 45);
		panel_2.add(date_BĐ);
                
            try {
                date_BĐ.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(today.plusDays(1).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(QL_NhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		date_KT = new JDateChooser();
		date_KT.setDateFormatString("dd/MM/yyyy");
		//date_KT.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 15));
                date_KT.getDateEditor().getUiComponent().setFont(new Font("Tahoma", Font.PLAIN, 15));
		date_KT.setBounds(482, 65, 162, 43);
		panel_2.add(date_KT);
		
            try {
                date_KT.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(today.plusDays(2).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(QL_NhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
                
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 200, 0), new Color(0, 255, 255), new Color(64, 64, 64), new Color(255, 0, 0)));
		panel_3.setBounds(10, 144, 654, 98);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		btn_ThemKM = new JButton("Thêm");
		btn_ThemKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themKhuyenMai();
			}
		});
		btn_ThemKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_ThemKM.setBounds(49, 33, 100, 32);
		panel_3.add(btn_ThemKM);
		
		btn_SuaKM = new JButton("Sửa");
		btn_SuaKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaKhuyenMai();
			}
		});
		btn_SuaKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_SuaKM.setBounds(187, 33, 100, 32);
                btn_SuaKM.setEnabled(false);
		panel_3.add(btn_SuaKM);
		
		btn_XoaKM = new JButton("Xóa");
		btn_XoaKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaKhuyenMai();
			}
		});
		btn_XoaKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_XoaKM.setBounds(353, 33, 100, 32);
                btn_XoaKM.setEnabled(false);
		panel_3.add(btn_XoaKM);
                
                JButton btn_Clear = new JButton("Clear");
		btn_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearData();
			}
		});
		btn_Clear.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_Clear.setBounds(489, 33, 100, 32);
                btn_Clear.setEnabled(true);
		panel_3.add(btn_Clear);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Danh sách khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 288, 654, 338);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 634, 303);
		panel_4.add(scrollPane);
		
		tbl_KM = new JTable();
		tbl_KM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableKhuyenMai();
			}
		});
		tbl_KM.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
			}
		));
		scrollPane.setViewportView(tbl_KM);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(694, 63, 400, 638);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Chi tiết khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(10, 10, 388, 173);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mã khuyến mãi :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(62, 10, 125, 30);
		panel_5.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Phần trăm giảm:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(62, 93, 125, 30);
		panel_5.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Điều kiện (>x) :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_5.setBounds(62, 133, 125, 30);
		panel_5.add(lblNewLabel_1_5);
		
		txt_MaKMCT = new JTextField();
		txt_MaKMCT.setColumns(10);
		txt_MaKMCT.setBounds(197, 14, 73, 27);
                txt_MaKMCT.setEditable(false);
		panel_5.add(txt_MaKMCT);
		
		txt_phanTram = new JTextField();
		txt_phanTram.setColumns(10);
		txt_phanTram.setBounds(197, 97, 73, 27);
		panel_5.add(txt_phanTram);
		
		txt_dieuKien = new JTextField();
		txt_dieuKien.setColumns(10);
		txt_dieuKien.setBounds(197, 136, 138, 27);
		panel_5.add(txt_dieuKien);
                
                JLabel donvitien = new JLabel("VNĐ");
		donvitien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		donvitien.setBounds(340, 136, 40, 27);
		panel_5.add(donvitien);
		
		JLabel lblNewLabel_2 = new JLabel("%");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(280, 102, 35, 13);
		panel_5.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Mã Sản phẩm :");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(62, 53, 125, 30);
		panel_5.add(lblNewLabel_1_3_1);
		
		/*txt_maSP = new JTextField();
		txt_maSP.setColumns(10);
		txt_maSP.setBounds(197, 51, 73, 27);
		panel_5.add(txt_maSP);*/
                
                cbx_sp = new JComboBox();
		cbx_sp.setMaximumRowCount(15);
		cbx_sp.setFont(new Font("Tahoma", Font.PLAIN, 19));
		cbx_sp.setBackground(Color.WHITE);
		cbx_sp.setBounds(191, 51, 180, 27);
		panel_5.add(cbx_sp);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(10, 193, 384, 98);
		panel_1.add(panel_3_1);
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 200, 0), new Color(0, 255, 255), new Color(64, 64, 64), new Color(255, 0, 0)));
		
		btn_ThemCTKM = new JButton("Thêm");
		btn_ThemCTKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themCTKM();
			}
		});
		btn_ThemCTKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_ThemCTKM.setBounds(10, 33, 100, 32);
                btn_ThemCTKM.setEnabled(false);
		panel_3_1.add(btn_ThemCTKM);
		
		btn_SuaCTKM = new JButton("Sửa");
		btn_SuaCTKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaCTKM();
			}
		});
		btn_SuaCTKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_SuaCTKM.setBounds(132, 33, 100, 32);
                btn_SuaCTKM.setEnabled(false);
		panel_3_1.add(btn_SuaCTKM);
		
		btn_XoaCTKM = new JButton("Xóa");
		btn_XoaCTKM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaCTKM();
			}
		});
		btn_XoaCTKM.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_XoaCTKM.setBounds(255, 33, 100, 32);
                btn_XoaCTKM.setEnabled(false);
		panel_3_1.add(btn_XoaCTKM);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 301, 380, 321);
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 360, 292);
		panel_6.add(scrollPane_1);
		
		tbl_CTKM = new JTable();
		tbl_CTKM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTableCTKhuyenMai();
			}
		});
		tbl_CTKM.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Mã Khuyến mãi", "Mã SP", "Phần trăm giảm", "Điều kiện"
			}
		));
		scrollPane_1.setViewportView(tbl_CTKM);
		
		JLabel lblNewLabel = new JLabel("Quản lý khuyến mãi");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(448, 10, 290, 43);
		contentPane.add(lblNewLabel);
		
		JButton btn_menu = new JButton("Trang Chính");
		btn_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFormGUI MF = new MainFormGUI();
				MF.setVisible(true);
				dispose();
			}
		});
		btn_menu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_menu.setBounds(492, 711, 216, 33);
		btn_menu.setIcon(new ImageIcon("Icon/home.png"));
		contentPane.add(btn_menu);
		loadingKhuyenMai();
		loadingCTKhuyenMai();
                loadingComBox();
	}
	private void loadingKhuyenMai() {
		model_KM.setRowCount(0);
                loadingComBox();
                KMBUS.docDanhSach();
                ArrayList<KhuyenMai> dskm = KMBUS.getDanhSachKhuyenMai();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector header = new Vector();
		header.add("Mã Khuyến mãi");
		header.add("Tên Khuyến mãi");
		header.add("Ngày bắt đầu");
		header.add("Ngày kết thúc");
		header.add("Tình trạng");
        
        if (model_KM.getRowCount()==0){ 
        	model_KM=new DefaultTableModel(header,0){
                    @Override
                    public boolean isCellEditable(int row,int column){
                        return false;
                    }
                };
        }
        if(dskm==null) return;
        for (KhuyenMai km : dskm) {
            Vector vec = new Vector();
            vec.add(km.getMaKM());
            vec.add(km.getTenKM());           
            vec.add(sdf.format(km.getNgayBD()));
            vec.add(sdf.format(km.getNgayKT()));

            Date now = new Date();
            if (km.getNgayBD().after(now)) {
                vec.add("Sớm bắt đầu");
            } else if(km.getNgayKT().before(now) || km.getNgayKT().compareTo(now)==0) {
                vec.add("Đã kết thúc");
            } else{
                vec.add("Đang bắt đầu");
            }
            model_KM.addRow(vec);
            tbl_KM.setModel(model_KM);
        }
      //căn giữa
      	centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      	for(int x=0;x<tbl_KM.getColumnCount();x++){
      	       tbl_KM.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
      	}
	}
	private void clickTableKhuyenMai() {
	int row = tbl_KM.getSelectedRow();
        if (row > -1) {
            String ma = tbl_KM.getValueAt(row, 0) + "";
            String ten = tbl_KM.getValueAt(row, 1) + "";           
            String start = tbl_KM.getValueAt(row, 2) + "";
            String end = tbl_KM.getValueAt(row, 3) + "";
            String status=tbl_KM.getValueAt(row,4) +"";
            java.util.Date ngayBD = new Date();
            java.util.Date ngayKT = new Date();
            try {
                ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(start);
                ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            } catch (Exception e) {
            }
            tbl_CTKM.clearSelection();
            txt_MaKM.setEditable(false);
            txt_MaKM.setText(ma);
            txt_TenKM.setText(ten);          
            date_BĐ.setDate(ngayBD);
            date_KT.setDate(ngayKT);
            txt_MaKMCT.setText(ma);
            btn_ThemKM.setEnabled(false);
            btn_SuaCTKM.setEnabled(false);
            btn_XoaCTKM.setEnabled(false);
            if(status.equals("Đang diễn ra") || status.equals("Đã kết thúc")){
                btn_XoaKM.setEnabled(false);
                btn_SuaKM.setEnabled(false);
                btn_ThemCTKM.setEnabled(false);
            }
            else{
                btn_XoaKM.setEnabled(true);
                btn_SuaKM.setEnabled(true);
                btn_ThemCTKM.setEnabled(true);
            }
            loadingCTKhuyenMai(ma);
        }
	}
	private void themKhuyenMai() {
            
		boolean flag = KMBUS.themKhuyenMai(
				txt_TenKM.getText(),
				date_BĐ.getDate(),
				date_KT.getDate());
		if(flag) {
			KMBUS.docDanhSach();
			loadingKhuyenMai();
		}
	}
        private void themKhuyenMai1() {
		boolean flag = KMBUS.themKhuyenMai1(
				Integer.parseInt(txt_MaKM.getText()),
				txt_TenKM.getText(),
				date_BĐ.getDate(),
				date_KT.getDate());
		if(flag) {
			KMBUS.docDanhSach();
			loadingKhuyenMai();
		}
	}
	private void suaKhuyenMai() {
		boolean flag =KMBUS.suaKhuyenMai(txt_MaKM.getText(),
				txt_TenKM.getText(),
				date_BĐ.getDate(),
				date_KT.getDate());
		if(flag) {
			KMBUS.docDanhSach();
			loadingKhuyenMai();
		}
	}
	private void xoaKhuyenMai() {
		String maKM = txt_MaKM.getText();
		MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
		if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = KMBUS.xoaKhuyenMai(maKM);
            if (flag) {
                loadingKhuyenMai();
            }
        }
		ArrayList<CTKhuyenMai> dsctkm = CTKMBUS.getDanhSachKhuyenMai();
		
		for(CTKhuyenMai ctkm : dsctkm) {
			if(ctkm.getMaKM()== Integer.parseInt(maKM)) {
				xoaCTKM(maKM);
			}
		}
		loadingCTKhuyenMai();
	}
        private void clearData(){
            txt_MaKM.setText("");
            txt_TenKM.setText("");
            txt_MaKMCT.setText("");
            txt_phanTram.setText("");
            txt_dieuKien.setText("");
            btn_SuaCTKM.setEnabled(false);
            btn_ThemCTKM.setEnabled(false);
            btn_XoaCTKM.setEnabled(false);
            btn_XoaKM.setEnabled(false);
            btn_SuaKM.setEnabled(false);
            btn_ThemKM.setEnabled(true);
            try {
                date_BĐ.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(today.plusDays(1).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(QL_NhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                date_KT.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(today.plusDays(2).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(QL_NhapHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadingKhuyenMai();
            loadingCTKhuyenMai();
        }
	private void loadingCTKhuyenMai() {
		model_CTKM.setRowCount(0);
                setDataCTPN_Default();
		CTKMBUS.docDanhSach();
		ArrayList<CTKhuyenMai> dsctkm = CTKMBUS.getDanhSachKhuyenMai();
		Vector header = new Vector();
		header.add("Mã Khuyến mãi");
		header.add("Mã SP");
		header.add("Phần trăm giảm");
		header.add("Điều Kiện Giảm");
		if(model_CTKM.getRowCount() == 0)
			model_CTKM = new DefaultTableModel(header,0){
                            @Override
                            public boolean isCellEditable(int row,int column){
                                return false;
                            }
                        };
		for( CTKhuyenMai i : dsctkm) {
			Vector vec = new Vector();
			vec.add(i.getMaKM());
			vec.add(i.getMaSP());
			vec.add(i.getPhanTramGiam());
			vec.add(i.getDieuKien());
			model_CTKM.addRow(vec);
			tbl_CTKM.setModel(model_CTKM);
		}
		// căn giữa
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < tbl_CTKM.getColumnCount(); x++) {
			tbl_CTKM.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
	}
        private void loadingCTKhuyenMai(String maKM) {
		model_CTKM.setRowCount(0);
                setDataCTPN_Default();
                txt_MaKMCT.setText(maKM);
		CTKMBUS.docDanhSach();
		ArrayList<CTKhuyenMai> dsctkm = CTKMBUS.getDanhSachKhuyenMai();
		Vector header = new Vector();
		header.add("Mã Khuyến mãi");
		header.add("Mã SP");
		header.add("Phần trăm giảm");
		header.add("Điều Kiện Giảm");
		if(model_CTKM.getRowCount() == 0)
			model_CTKM = new DefaultTableModel(header,0){
                            @Override
                            public boolean isCellEditable(int row,int column){
                                return false;
                            }
                        };
		for( CTKhuyenMai i : dsctkm) {
                    if(maKM.equals(i.getMaKM()+"")){
                        Vector vec = new Vector();
			vec.add(i.getMaKM());
			vec.add(i.getMaSP());
			vec.add(i.getPhanTramGiam());
			vec.add(i.getDieuKien());
			model_CTKM.addRow(vec);
			tbl_CTKM.setModel(model_CTKM);
                    }
		}
		// căn giữa
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int x = 0; x < tbl_CTKM.getColumnCount(); x++) {
			tbl_CTKM.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
	}
        
	private void clickTableCTKhuyenMai() {
		int row = tbl_CTKM.getSelectedRow();
		if(row > -1) {
			String ma = tbl_CTKM.getValueAt(row, 0)+"";
			String masp = tbl_CTKM.getValueAt(row, 1)+"";
			String phantram = tbl_CTKM.getValueAt(row, 2)+"";
			String dieukien = tbl_CTKM.getValueAt(row, 3)+"";	
			txt_MaKMCT.setText(ma);
			String[] index1 = masp.split(" ");		            
                        cbx_sp.setSelectedIndex(Integer.parseInt(index1[0]));
			txt_dieuKien.setText(dieukien);
			txt_phanTram.setText(phantram);
                        btn_ThemCTKM.setEnabled(false);
                        btn_SuaCTKM.setEnabled(true);
                        btn_XoaCTKM.setEnabled(true);
		}
	}
	private void themCTKM() {
            String maSP=cbx_sp.getItemAt(cbx_sp.getSelectedIndex())+"";
            boolean flag = CTKMBUS.themCTKhuyenMai(txt_MaKMCT.getText(),maSP,txt_phanTram.getText(), txt_dieuKien.getText());
		if(flag) {
			CTKMBUS.docDanhSach();
			loadingCTKhuyenMai();
		}
	}
	private void suaCTKM() {
            String maSP=cbx_sp.getItemAt(cbx_sp.getSelectedIndex())+"";
		boolean flag = CTKMBUS.suaCTKhuyenMai(txt_MaKMCT.getText(), maSP, txt_phanTram.getText(), txt_dieuKien.getText());
		if(flag) {
			CTKMBUS.docDanhSach();
			loadingCTKhuyenMai();
		}
	}
	private void xoaCTKM() {
            String maSP=cbx_sp.getItemAt(cbx_sp.getSelectedIndex())+"";
		MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xóa ?", MyDialog.WARNING_DIALOG);
		if(dlg.OK_OPTION == dlg.getAction()) {
			boolean flag = CTKMBUS.xoaCTKhuyenMai(txt_MaKMCT.getText(),maSP);
			if(flag)
				loadingCTKhuyenMai();
		}
	}
        
        private void setDataCTPN_Default(){
            txt_MaKMCT.setText("");
            txt_phanTram.setText("");
            txt_dieuKien.setText("");
        }
        
        private void loadingComBox() {
        cbx_sp.removeAllItems();
        
        ArrayList<SanPham> dssp = spBus.getListSanPham();
        cbx_sp.addItem("Chọn SP");
        for (SanPham sp : dssp) {       	
        	cbx_sp.addItem(sp.getMaSP()+" - "+sp.getTenSP());
        }
    }

	private void xoaCTKM(String maKM) {
		maKM = txt_MaKMCT.getText();
		CTKMBUS.xoaCTKhuyenMai(maKM);		
		loadingCTKhuyenMai();
	}
}
