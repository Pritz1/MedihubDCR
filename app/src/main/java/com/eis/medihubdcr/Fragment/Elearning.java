package com.eis.medihubdcr.Fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eis.medihubdcr.Activity.Quiz;
import com.eis.medihubdcr.Api.RetrofitClient;
import com.eis.medihubdcr.Others.Global;
import com.eis.medihubdcr.Others.ViewDialog;
import com.eis.medihubdcr.Pojo.EleaningMainRes;
import com.eis.medihubdcr.Pojo.ForthtestlstItem;
import com.eis.medihubdcr.Pojo.TestlstItem;
import com.eis.medihubdcr.Pojo.TestresultlstItem;
import com.eis.medihubdcr.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Elearning extends Fragment {

    View view;
    RecyclerView testavaillst,testforthllst,testresult;
    ViewDialog progressDialoge;
    List<TestlstItem> testlst = new ArrayList<>();
    List<ForthtestlstItem> forthtestlst = new ArrayList<>();
    List<TestresultlstItem> testresultlst = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("E-LEARNING");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_elearning, container, false);

        testavaillst = view.findViewById(R.id.testavaillst);
        testavaillst.setVisibility(View.GONE);
        testforthllst = view.findViewById(R.id.testforthllst);
        testforthllst.setVisibility(View.GONE);
        testresult = view.findViewById(R.id.testresult);
        testresult.setVisibility(View.GONE);
        progressDialoge = new ViewDialog(getActivity());
        testListAdapter();
        ForthTestListAdapter();
        testResultAdapter();
        callApi();
        return view;
    }

    private void testResultAdapter() {
        testresult.setNestedScrollingEnabled(false);
        testresult.setLayoutManager(new LinearLayoutManager(getActivity()));
        testresult.setAdapter(new RecyclerView.Adapter() {
                                   @NonNull
                                   @Override
                                   public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                       View view = LayoutInflater.from(getActivity()).inflate(R.layout.test_result_adapter, viewGroup, false);
                                       Holder holder = new Holder(view);
                                       return holder;
                                   }

                                   @Override
                                   public long getItemId(int position) {
                                       return position;
                                   }

                                   @Override
                                   public int getItemViewType(int position) {
                                       return position;
                                   }

                                   @Override
                                   public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
                                       final Holder myHolder = (Holder) viewHolder;
                                       final TestresultlstItem model = testresultlst.get(i);
                                       myHolder.testnamead.setText(model.getTestName());
                                       myHolder.percent.setText(model.getPercentage()+" %");
                                       myHolder.attempts.setText(model.getAttemptNo());
                                       myHolder.timetaken.setText(model.getTimeTaken());
                                       myHolder.rank.setText("");

                                   }

                                   @Override
                                   public int getItemCount() {
                                       return testresultlst.size();
                                   }

                                   class Holder extends RecyclerView.ViewHolder {
                                       TextView testnamead,percent,attempts,timetaken,rank;

                                       public Holder(@NonNull View itemView) {
                                           super(itemView);
                                           testnamead = itemView.findViewById(R.id.testnamead);
                                           percent = itemView.findViewById(R.id.percent);
                                           attempts = itemView.findViewById(R.id.attempts);
                                           timetaken = itemView.findViewById(R.id.timetaken);
                                           rank = itemView.findViewById(R.id.rank);
                                       }
                                   }
                               }
        );
    }

    private void ForthTestListAdapter() {
        testforthllst.setNestedScrollingEnabled(false);
        testforthllst.setLayoutManager(new LinearLayoutManager(getActivity()));
        testforthllst.setAdapter(new RecyclerView.Adapter() {
                                  @NonNull
                                  @Override
                                  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                      View view = LayoutInflater.from(getActivity()).inflate(R.layout.test_list_adapter, viewGroup, false);
                                      Holder holder = new Holder(view);
                                      return holder;
                                  }

                                  @Override
                                  public long getItemId(int position) {
                                      return position;
                                  }

                                  @Override
                                  public int getItemViewType(int position) {
                                      return position;
                                  }

                                  @Override
                                  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
                                      final Holder myHolder = (Holder) viewHolder;
                                      final ForthtestlstItem model = forthtestlst.get(i);
                                      myHolder.testnamead.setText(model.getTestName());
                                      myHolder.stdate.setText(model.getActiveDateFrom());
                                      myHolder.enddate.setText(model.getActiveDateTo());
                                  }

                                  @Override
                                  public int getItemCount() {
                                      return forthtestlst.size();
                                  }

                                  class Holder extends RecyclerView.ViewHolder {
                                      TextView testnamead,stdate,enddate;

                                      public Holder(@NonNull View itemView) {
                                          super(itemView);
                                          testnamead = itemView.findViewById(R.id.testname);
                                          stdate = itemView.findViewById(R.id.stdate);
                                          enddate = itemView.findViewById(R.id.enddate);
                                      }
                                  }
                              }
        );
    }

    private void testListAdapter() {
        testavaillst.setNestedScrollingEnabled(false);
        testavaillst.setLayoutManager(new LinearLayoutManager(getActivity()));
        testavaillst.setAdapter(new RecyclerView.Adapter() {
                                     @NonNull
                                     @Override
                                     public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                         View view = LayoutInflater.from(getActivity()).inflate(R.layout.test_list_adapter, viewGroup, false);
                                         Holder holder = new Holder(view);
                                         return holder;
                                     }

                                     @Override
                                     public long getItemId(int position) {
                                         return position;
                                     }

                                     @Override
                                     public int getItemViewType(int position) {
                                         return position;
                                     }

                                     @Override
                                     public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
                                         final Holder myHolder = (Holder) viewHolder;
                                         final TestlstItem model = testlst.get(i);
                                         myHolder.testnamead.setText(Html.fromHtml("<u>"+model.getTestName()+"</u>"));
                                         myHolder.stdate.setText(model.getActiveDateFrom());
                                         myHolder.enddate.setText(model.getActiveDateTo());
                                         myHolder.itemView.setTag(i);
                                         myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent intent = new Intent(getActivity(), Quiz.class);
                                                 intent.putExtra("testid", model.getTestId());
                                                 intent.putExtra("testname", model.getTestName());
                                                 intent.putExtra("totques", model.getNoOfQuestions());
                                                 intent.putExtra("time", model.getTotalTime());
                                                 Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.trans_left_in, R.anim.trans_left_out).toBundle();
                                                 startActivity(intent, bndlanimation);
                                             }
                                         });
                                     }

                                     @Override
                                     public int getItemCount() {
                                         return testlst.size();
                                     }

                                     class Holder extends RecyclerView.ViewHolder {
                                         TextView testnamead,stdate,enddate;

                                         public Holder(@NonNull View itemView) {
                                             super(itemView);
                                             testnamead = itemView.findViewById(R.id.testname);
                                             stdate = itemView.findViewById(R.id.stdate);
                                             enddate = itemView.findViewById(R.id.enddate);
                                         }
                                     }
                                 }
        );
    }

    private void callApi() {
        progressDialoge.show();
        Call<EleaningMainRes> call1 = RetrofitClient
                .getInstance().getApi().getElearningData(Global.ecode, Global.dbprefix);
        call1.enqueue(new Callback<EleaningMainRes>() {
            @Override
            public void onResponse(Call<EleaningMainRes> call1, Response<EleaningMainRes> response) {
                EleaningMainRes res = response.body();
                progressDialoge.dismiss();
                assert res != null;
                if (res.isShowtest()) {
                    testlst = res.getTestlst();
                    testavaillst.setVisibility(View.VISIBLE);
                    testavaillst.getAdapter().notifyDataSetChanged();
                }

                if (res.isShowforthtest()) {
                    forthtestlst = res.getForthtestlst();
                    testforthllst.setVisibility(View.VISIBLE);
                    testforthllst.getAdapter().notifyDataSetChanged();
                }

                if (res.isShowresult()) {
                    testresultlst = res.getTestresultlst();
                    testresult.setVisibility(View.VISIBLE);
                    testresult.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EleaningMainRes> call1, Throwable t) {
                progressDialoge.dismiss();
                Toast.makeText(getActivity(), "Failed to fetch data !", Toast.LENGTH_LONG).show();
            }
        });
    }

}
