package expansion.neto.com.mx.gerenteapp.sorted.autoriza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.DummyModel;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autoriza;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaModel;
import expansion.neto.com.mx.gerenteapp.utils.ImageUtil;

public class AdapterCardAutoriza extends ArrayAdapter<AutorizaModel> implements View.OnClickListener {

	private LayoutInflater mInflater;

	public AdapterCardAutoriza(Context context, List<AutorizaModel> items) {
		super(context, 0, items);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getmId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.list_item_google_cards_media, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.list_item_google_cards_media_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		AutorizaModel item = getItem(position);
		ImageUtil.displayImage(holder.image, item.getmImageURL(), null);

		return convertView;
	}

	private static class ViewHolder {
		public ImageView image;
		public TextView artistName;
		public TextView year;
		public TextView text;
		public TextView country;
		public TextView like;
		public TextView favorite;
		public TextView share;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int possition = (Integer) v.getTag();
		switch (v.getId()) {
		}
	}



}
