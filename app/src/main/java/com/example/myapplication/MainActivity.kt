package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this);
    }

    override fun onClick(view: View) {
        if (view?.id == R.id.btn_calculate) {
            val inputLength =
                edtLength.getText().toString().trim();
            val inputWidth =
                edtWidth.getText().toString().trim();
            val inputHeight =
                edtHeight.getText().toString().trim();


            var isEmptyFields = false;
            var isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLength.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtWidth.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            }

            val length = convertToDouble(inputLength)
            val width = convertToDouble(inputWidth)
            val height = convertToDouble(inputHeight)

            if (length == null) {
                isInvalidDouble = true;
                edtLength.setError("Field ini harus berupa nomor yang valid")
            }

            if (width == null) {
                isInvalidDouble = true;
                edtWidth.setError("Field ini harus berupa nomor yang valid")
            }

            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa nomor yang valid")
            }

            if (!isEmptyFields && !isInvalidDouble) {
                val volume = height!!.toDouble() * length!!.toDouble() * width!!.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
    private fun convertToDouble(str: String):Double?{
        return try {
            str.toDouble()
        }catch (e : NumberFormatException){
            null
        }
    }

    companion object{
        private const val STATE_RESULT = "state_result"
    }
}