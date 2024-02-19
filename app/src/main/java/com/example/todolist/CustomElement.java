package com.example.todolist;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.lang.Math;


public class CustomElement extends androidx.appcompat.widget.AppCompatButton {

    public CustomElement(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int hauteur = getMeasuredHeight();
        int largeur = getMeasuredWidth();

        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);

        paint.setStyle(Paint.Style.FILL);
        Path path=new Path();
        path.lineTo(0, 0);
        path.lineTo(0, hauteur);
        path.lineTo(largeur, hauteur);
        path.lineTo(largeur, 0);
        canvas.drawPath(path, paint);

    }
}