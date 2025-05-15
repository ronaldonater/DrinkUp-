package com.example.drinkup.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\fH\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0007J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/drinkup/data/Converters;", "", "()V", "dateFormatter", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "dateTimeFormatter", "dateTimeToTimestamp", "", "date", "Ljava/time/LocalDateTime;", "dateToString", "Ljava/time/LocalDate;", "fromDateString", "value", "fromTimestamp", "app_debug"})
public final class Converters {
    private final java.time.format.DateTimeFormatter dateTimeFormatter = null;
    private final java.time.format.DateTimeFormatter dateFormatter = null;
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final java.time.LocalDateTime fromTimestamp(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final java.lang.String dateTimeToTimestamp(@org.jetbrains.annotations.Nullable
    java.time.LocalDateTime date) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final java.time.LocalDate fromDateString(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.Nullable
    public final java.lang.String dateToString(@org.jetbrains.annotations.Nullable
    java.time.LocalDate date) {
        return null;
    }
}