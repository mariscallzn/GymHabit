package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
// region ExerciseDao
// TODO: May be I can implement in the future the Paging from JetPack with this
@Dao
interface ExerciseDao {

    @Insert
    fun insertAll(exercises: List<ExerciseEntity>)

    @Query("SELECT * FROM exercises")
    fun getAll(): Flow<ExerciseEntity>
}
// endregion

// region MuscleDao
@Dao
interface MuscleDao {
    @Insert
    fun insertAll(exercises: List<MuscleEntity>)

    @Query("SELECT * FROM muscles")
    fun getAll(): Flow<MuscleEntity>
}
// endregion

//region EquipmentDao
@Dao
interface EquipmentDao {
    @Insert
    fun insertAll(exercises: List<EquipmentEntity>)

    @Query("SELECT * FROM equipments")
    fun getAll(): Flow<EquipmentEntity>
}
// endregion

// region ExerciseDao
@Dao
interface ExerciseTypeDao {
    @Insert
    fun insertAll(exercises: List<ExerciseTypeEntity>)

    @Query("SELECT * FROM exercise_types")
    fun getAll(): Flow<ExerciseTypeEntity>
}
// endregion
