package com.cpxiao.idleballz;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpxiao.AppConfig;
import com.cpxiao.R;
import com.cpxiao.idleballz.mode.ItemData;
import com.cpxiao.idleballz.mode.extra.BallsExtra;
import com.cpxiao.idleballz.mode.extra.GameExtra;

import java.util.List;


/**
 * @author cpxiao on 2017/9/28.
 */

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalViewHolder> {
    private static final boolean DEBUG = AppConfig.DEBUG;
    private static final String TAG = NormalRecyclerViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<ItemData> mDataList;

    public NormalRecyclerViewAdapter(Context context, List<ItemData> list) {
        mContext = context;
        mDataList = list;
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        params.width = (int) (parent.getMeasuredWidth() / 5F);
        view.setLayoutParams(params);
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position) {
        if (holder == null || mDataList == null) {
            if (DEBUG) {
                throw new IllegalArgumentException("holder == null || mDataList == null");
            }
            return;
        }
        if (position < 0 || position >= mDataList.size()) {
            if (DEBUG) {
                throw new IllegalArgumentException("position < 0 || position >= mDataList.size()");
            }
            return;
        }
        final int index = holder.getAdapterPosition();
        final ItemData data = mDataList.get(index);
        holder.mTitle.setText(data.title);
        if (data.level > 0) {
            holder.mLevel.setText(mContext.getString(R.string.level) + " " + data.level);
        } else {
            holder.mLevel.setText("");
        }
        holder.mIcon.setImageResource(data.resId);
        holder.mPower.setText(GameExtra.format2(data.power));
        holder.mUpdateTextView.setText(GameExtra.format1(data.updatePrice));
        if (data.isCanBeUpdated) {
            holder.mUpdateLayout.setClickable(true);
            holder.mUpdateLayout.setBackgroundResource(R.drawable.home_btn_bg);
            holder.mUpdateLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DEBUG) {
                        Log.d(TAG, "onClick: ......");
                    }
                    //the item ball level/power/price up
                    data.level++;
                    data.power = BallsExtra.getPower(index, data.level);
                    data.updatePrice = BallsExtra.getUpdatePrice(index, data.level);

                    //the game view ball power up
                    if (mOnItemClicked != null) {
                        mOnItemClicked.onItemClicked(index, data.updatePrice, data.level);
                    } else {
                        if (DEBUG) {
                            Log.d(TAG, "onClick: ....mOnItemClicked == null");
                        }
                    }
                }
            });
        } else {
            holder.mUpdateLayout.setClickable(false);
            holder.mUpdateLayout.setBackgroundColor(Color.GRAY);
        }

        if (data.level <= 0) {
            holder.mUpdateImageView.setImageResource(R.drawable.locked);
        } else {
            holder.mUpdateImageView.setImageResource(R.drawable.update);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mLevel;
        ImageView mIcon;
        TextView mPower;
        LinearLayout mUpdateLayout;
        ImageView mUpdateImageView;
        TextView mUpdateTextView;

        NormalViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mLevel = (TextView) itemView.findViewById(R.id.level);
            mIcon = (ImageView) itemView.findViewById(R.id.icon);
            mPower = (TextView) itemView.findViewById(R.id.power);
            mUpdateLayout = (LinearLayout) itemView.findViewById(R.id.updateLayout);
            mUpdateImageView = (ImageView) itemView.findViewById(R.id.updateImageView);
            mUpdateTextView = (TextView) itemView.findViewById(R.id.updateTextView);
        }
    }

    private OnItemClicked mOnItemClicked;

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        mOnItemClicked = onItemClicked;
    }
}
