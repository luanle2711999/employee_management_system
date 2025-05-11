package com.example.employee_management_system.enums;

public enum PermissionName {
    EMPLOYEE_VIEW("View employee profiles and details"),
    EMPLOYEE_CREATE("Add new employee records"),
    EMPLOYEE_UPDATE("Edit existing employee information"),
    EMPLOYEE_DELETE("Remove employee records from the system"),
    EMPLOYEE_EXPORT("Export employee data to files (e.g., CSV, Excel)"),

    LEAVE_VIEW_ALL("View leave requests of all employees"),
    LEAVE_APPROVE("Approve leave requests submitted by employees"),
    LEAVE_REJECT("Reject leave requests submitted by employees"),

    REPORT_VIEW("Access and view system-generated reports"),
    REPORT_EXPORT("Export reports to downloadable formats"),

    ATTENDANCE_MANAGE_TEAM("Manage attendance records for team members"),

    LEAVE_VIEW_SELF("View own leave records and status"),
    LEAVE_APPLY("Submit leave requests for oneself"),

    ATTENDANCE_VIEW("View attendance records (own or team's, depending on role)"),
    ATTENDANCE_CREATE("Add new attendance entries"),
    ATTENDANCE_UPDATE("Edit or correct existing attendance records"),

    PAYROLL_VIEW("View payroll information for employees"),
    PAYROLL_GENERATE("Generate payroll for a given period"),
    PAYROLL_EXPORT("Export payroll data for reporting or processing"),

    DEVICE_VIEW("View list of devices assigned to employees"),
    DEVICE_ASSIGN("Assign a device to an employee"),
    DEVICE_REVOKE("Revoke or unassign a device from an employee"),

    TOOL_ACCESS_MANAGE("Manage access rights to tools and applications");

    private String description;

    PermissionName(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
