package com.example.cashflow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cashflow.data.dao.ExpenseDao
import com.example.cashflow.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDataBase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDataBase {
            return Room.databaseBuilder(context, ExpenseDataBase::class.java, DATABASE_NAME)
                .addCallback(object : Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        InitBasicData(context)
                    }
                    fun InitBasicData(context: Context){
                        CoroutineScope(Dispatchers.IO).launch {
                            val Dao = getDatabase(context).expenseDao()
                            Dao.insertExpense(ExpenseEntity(1, "salary",50000.34 ,
                                System.currentTimeMillis().toString(), "Salary","Income"))
                            Dao.insertExpense(ExpenseEntity(2, "Rent", 3000.10,
                                System.currentTimeMillis().toString(),"Rent","Expense"))
                            Dao.insertExpense(ExpenseEntity(3, "Grocery", 2000.20,
                                System.currentTimeMillis().toString(),"Netflix","Expense"))
                            Dao.insertExpense(ExpenseEntity(4, "Freelancing",40000.45,
                                System.currentTimeMillis().toString(),"Starbucks","Income"))
                            Dao.insertExpense(ExpenseEntity(5, "Rent", 3000.54,
                                System.currentTimeMillis().toString(),"Paypal","Expense"))
                        }
                    }
                })
                .build()
        }
    }

}
