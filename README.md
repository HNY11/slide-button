# *SlideButton*  
SlideButton is a simple library with beautiful animation
# Demo  
![screenrecorder-2018-10-12-22-08-05-586](https://user-images.githubusercontent.com/43207796/46882947-2e323200-ce05-11e8-9ed6-8677fe9e8373.gif)
# How To Use?  

**Step 1. Add it in your root build.gradle at the end of repositories:**  
```ruby
allprojects {
	repositories{
			...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2. Add the dependency**  
```ruby
dependencies {
	        implementation 'com.github.LokikSoni:SlideButton:v0.3-beta'
	}
```
**Step 3. Add the View**  
```ruby
<com.greenlab.hackme.slidebutton.SlideButton
         android:id="@+id/btnUp"
        android:layout_width="260dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center">
    </com.greenlab.hackme.slidebutton.SlideButton>
```
**Step 4. Implement Listener** 
```ruby
public class MainActivity extends AppCompatActivity implements SlideButton.SlideListener {

    SlideButton slideButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideButton1=findViewById(R.id.btnUp);
        slideButton1.setOnSlideListener(this);
    }

    @Override
    public void onClick(SlideButton mSlideButton, boolean active) {

        if(mSlideButton.getId()==R.id.btnUp) {

            if(active){
                Toast.makeText(this, "Active Up", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Deactivate Up", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
```
# *Customization*  
| | Button | |
| --- | --- | --- |
| **Attributes** | **Description** | **Value** |
| `app:buttonPadding` | set the **padding of a button** | 20dp
| `app:cornerRadius` | Set the **corner radius of button** | 45dp
| `app:collapseColor` | Set the **color of button in collapse** state | android:color/white
| `app:expandColor` | Set the **color of button in expand** state | android:color/white
| `app:collapseIcon` | Set the **icon of button in collapse** state | drawable/collapsed
| `app:expandIcon` | Set the **icon of button in expand** state | drawable/expanded
| `app:strokeColor` | Set the **outline color of button** | #ee071a32
| `app:strokeWidth` | Set the **outline stroke width of button** | 3dp   


| | Text | |
| --- | --- | --- |
| **Attributes** | **Description** | **Value** |
| `app:text` | set the **text of button** | Tap to Activate
| `app:textPadding` | set the **padding of a text** | 20dp
| `app:textSize` | Set the **size of text** | 8dp
| `app:textColor` | Set the **color of text** | #a39c9c


| | Background | |
| --- | --- | --- |
| **Attributes** | **Description** | **Value** |
| `app:backColor` | set the **back color of button background** | #ee071a32
| `app:backStrokeColor` | Set the **outline stroke color of button background** | android:color/white
| `app:backStrokeWidth` |  Set the **outline stroke width of button background** | 2dp

# Thanks
# :blush:
