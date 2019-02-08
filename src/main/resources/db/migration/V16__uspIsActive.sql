CREATE PROCEDURE uspIsActive
	-- Add the parameters for the stored procedure here
	@active bit,
	@email Nvarchar
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	Update Users
	SET Active = @active WHERE Email = @email
END