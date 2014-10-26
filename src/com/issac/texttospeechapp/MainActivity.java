package com.issac.texttospeechapp;

import java.util.ArrayList;

import com.example.texttospeechapp.R;
import com.ipaulpro.afilechooser.utils.FileUtils;
import com.issac.texttospeechapp.adapters.CustomListAdapter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.*;



public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (position == 0 || position==2) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							PlaceholderFragment.newInstance(position))
        .commit();
        } else if (position==1) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							PDFModeFragment.newInstance(position))
					.commit();
		}
		
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        ArrayList<String> listData = new ArrayList<String>();
        
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            listData.add("SAMPLE DATA SAMPLE DATA 1");
            listData.add("SAMPLE DATA SAMPLE DATA 2");
            ListView list = (ListView)rootView.findViewById(R.id.readTextList);
            CustomListAdapter adapter = new CustomListAdapter(getActivity(),listData.toArray(new String[listData.size()]));
            list.setAdapter(adapter);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
/**
 *  PDF Management Class
 * @author MAYANK
 *
 */
    public static class PDFModeFragment extends Fragment {
    	private static final String ARG_SECTION_NUMBER = "section_number";
    	private static final int REQUEST_CHOOSER = 1234;
        
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PDFModeFragment newInstance(int sectionNumber) {
            PDFModeFragment fragment = new PDFModeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PDFModeFragment() {
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	View rootView = inflater.inflate(R.layout.fragment_pdf, container, false);
        	EditText pdfViewRegion = (EditText) rootView.findViewById(R.id.pdfViewer);
        	Button readPDFButton = (Button) rootView.findViewById(R.id.readPDFButton);
        	readPDFButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent getContentIntent = FileUtils.createGetContentIntent();

				    Intent intent = Intent.createChooser(getContentIntent, "Select a file");
				    startActivityForResult(intent, REQUEST_CHOOSER); 
					
				}
			});
        	return rootView;
        }
    }
}
