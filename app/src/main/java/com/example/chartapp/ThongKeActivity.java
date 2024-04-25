package com.example.chartapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chartapp.Model.ChiTietDonHang;
import com.example.chartapp.Model.DoanhThu;
import com.example.chartapp.Model.DonHang;
import com.example.chartapp.Model.HoaDon;
import com.example.chartapp.Model.ResponseObject;
import com.example.chartapp.Model.SanPham;
import com.example.chartapp.api.BaoCaoApi;
import com.example.chartapp.retrofit.RetrofitService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeActivity extends AppCompatActivity {
    public String getYear;
    private int delay = 1000;
    Toolbar toolbar;

    Button btnGetYearBill;
    PieChart pieChart;
    BarChart barChart;
    TextView tvTitle;
    List<SanPham> listSP = new ArrayList<>();
    List<DonHang> listDH = new ArrayList<>();
    List<ChiTietDonHang> listCTDH = new ArrayList<>();
    List<HoaDon> listHD = new ArrayList<>();
    List<String> yearsList = new ArrayList<>();
    private ArrayAdapter<String> yearAdapter;


    int[] colors = new int[] {
            Color.rgb(255, 102, 0),   // Màu cam
            Color.rgb(255, 204, 0),   // Màu vàng
            Color.rgb(51, 102, 204),  // Màu xanh dương
            Color.rgb(153, 0, 204),   // Màu tím
            Color.rgb(255, 51, 153),  // Màu hồng
            Color.rgb(0, 153, 204),   // Màu lam
            Color.rgb(204, 0, 0),     // Màu đỏ
            Color.rgb(0, 204, 102),   // Màu xanh lá
            Color.rgb(153, 153, 153), // Màu xám
            Color.rgb(0, 204, 204),   // Màu cyan
            Color.rgb(255, 0, 255),   // Màu magenta
            Color.rgb(0, 128, 128)    // Màu teal
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setControl();
        getDataSanPham();
        yearsList = generateYearsList();
        actionBar();
        btnGetYearBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void setControl() {
        toolbar = findViewById(R.id.toolbar);
        pieChart = findViewById(R.id.pieChart);
        barChart = findViewById(R.id.barChart);
        tvTitle = findViewById(R.id.tvTitle);
        btnGetYearBill = findViewById(R.id.btnGetYear);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_thong_ke, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.icTKSP) {
            getDataChiTietDonHang();
            return true;
        }
        else if(id == R.id.icDoanhThu) {
            getDoanhThuTheoThang();
            return true;
        }
        else {
            finish();
            return true;
        }

    }
    private String tongTienThang(List<HoaDon> cHoaDons, int ngayTao) {
        int tong = 0;
        for (HoaDon tmp : cHoaDons) {
            if (ngayTao == getMonth(tmp.getNgayTao())) {
                tong += tmp.getTongGiaTri();
            }
        }
        return tong + "";

    }
    private void showBarChart(List<DoanhThu> doanhThuList) {
        barChart.setVisibility(View.VISIBLE);
        pieChart.setVisibility(View.GONE);
        barChart.getAxisRight().setDrawLabels (false);
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int thang = doanhThuList.get(i).getThang();
            String tongTien = String.valueOf(doanhThuList.get(i).getTong());
            barEntries.add(new BarEntry(thang, Float.parseFloat(tongTien)));
        }
        barChart.getAxisRight().setDrawLabels (false);
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        BarDataSet dataSet = new BarDataSet(barEntries, "Biểu đồ doanh thu năm " + getYear);
        dataSet.setValueTextSize(12f);
        dataSet.setColors (colors);
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.animateXY(delay, delay);
        barChart.invalidate();
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
    }
    private int getMonth(String ngay)  {

        // 2002-03-07
        return Integer.parseInt(ngay.substring(5, 7));
    }
    private void showPieChart() {
        barChart.setVisibility(View.GONE);
        pieChart.setVisibility(View.VISIBLE);
        ArrayList<PieEntry> dataChart = new ArrayList<>();
        for (SanPham tmp : listSP) {
            int tong = tongSanPham(listCTDH, tmp.getMaSP());
            String name = tmp.getTenSanPham();
            dataChart.add(new PieEntry(tong, name));
        }
        PieDataSet pieDataSet = new PieDataSet(dataChart, "Biểu đồ");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);
        pieChart.animateXY(delay, delay);
        pieChart.setUsePercentValues(true);
        pieChart.invalidate();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                if (entry instanceof PieEntry) {
                    PieEntry selectedEntry = (PieEntry) entry;
                    String productName = selectedEntry.getLabel();
                    Toast.makeText(getApplicationContext(), "Sản phẩm: " + productName, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected() {
            }
        });

    }

    private List<HoaDon> getDataBillByYear(String year) {
        int intYear = Integer.parseInt(year);
        List<HoaDon> billYear = new ArrayList<>();
        for (HoaDon tmp : listHD) {
            int getYearInt = Integer.parseInt(tmp.getNgayTao().substring(0, 4));
            if (getYearInt == intYear) {
                billYear.add(tmp);
            }
        }
        return billYear;
    }

    private void getDoanhThuTheoThang() {

        RetrofitService retrofitService = new RetrofitService();
        BaoCaoApi baoCaoApi = retrofitService.getRetrofit().create(BaoCaoApi.class);
        baoCaoApi.layBaoCaoDoanhThu(Integer.parseInt(getYear)).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
              ResponseObject responseObject = response.body();
                List<DoanhThu> doanhThuList = new ArrayList<>();
                Log.e("Object: ", responseObject.getObject()+"");
                Toast.makeText(ThongKeActivity.this, responseObject.getObject() +"", Toast.LENGTH_SHORT).show();
                Toast.makeText(ThongKeActivity.this, doanhThuList.size() +"", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ResponseObject> call, Throwable throwable) {
                Toast.makeText(ThongKeActivity.this, "Chua có ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataHoaDon() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("HoaDon");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listHD.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    HoaDon tmp = dataSnapshot.getValue(HoaDon.class);
                    listHD.add(tmp);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getDataChiTietDonHang() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("ChiTietDonHang");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCTDH.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChiTietDonHang tmp = dataSnapshot.getValue(ChiTietDonHang.class);
                    listCTDH.add(tmp);
                }
                showPieChart();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showDatePickerDialog() {
        yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, yearsList);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn năm");
        builder.setAdapter(yearAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedYear = yearsList.get(which);
                getYear = selectedYear;
//                showBarChart();
            }
        });
        builder.show();

    }

    private List<String> generateYearsList() {
        List<String> yearsList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= 2000; i--) {
            yearsList.add(String.valueOf(i));
        }
        return yearsList;
    }
    private void getDataSanPham() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("SanPham");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSP.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SanPham tmp = dataSnapshot.getValue(SanPham.class);
                    listSP.add(tmp);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private int tongSanPham(List<ChiTietDonHang> chiTietDonHangs, String sanPham) {
        int tong = 0;
        for(ChiTietDonHang tmp : chiTietDonHangs) {
            if(sanPham.equals(tmp.getMaSanPham())) {
                tong += tmp.getSoLuong();
            }
        }
        return tong;
    }
    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataDonHang() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("DonHang");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDH.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DonHang tmp = dataSnapshot.getValue(DonHang.class);
                    listDH.add(tmp);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}