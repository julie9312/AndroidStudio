package com.julie.employee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.julie.employee.R;
import com.julie.employee.model.Employee;

import java.util.ArrayList;

   //2.상속
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    //3.멤버변수 선언
    Context context;
    ArrayList<Employee> employeeArrayList;
    //4.생성자
    public RecyclerViewAdapter(Context context,ArrayList<Employee> employeeArrayList){
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    Employee employee = employeeArrayList.get(position);
    String name = employee.getName();
    int age = employee.getAge();
    int salary = employee.getSalary();

    holder.txtName.setText(name);
    holder.txtAge.setText("나이 : "+age +"세");
    holder.txtSalary.setText("연봉 : $" +salary);
    }

    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }


    //1.뷰홀더 클래스 먼저 만든다.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtAge;
        public TextView txtSalary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);

        }

         }
     }