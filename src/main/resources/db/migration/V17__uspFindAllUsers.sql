CREATE PROCEDURE uspFindAllUsers
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
    -- Insert statements for procedure here
	SELECT  FirstName,LastName, Email, PhoneNumber, CompanySize FROM Users
END
GO